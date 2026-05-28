package br.com.planet.ixcwatchtask.token;

import br.com.planet.ixcwatchtask.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class WatchToken {

    private static final Logger log = LoggerFactory.getLogger(WatchToken.class);
    private static final Logger taskLog = LoggerFactory.getLogger("IXC_WATCH_TASK");
    private static final DateTimeFormatter SCREENSHOT_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final SeleniumConfig seleniumConfig;
    private WebDriver driver;
    private WebDriverWait wait;
    private final String login;
    private final String senha;
    private final boolean debug;
    private final String watchIntegrationId;

    @Autowired
    public WatchToken(
            SeleniumConfig seleniumConfig,
            @Value("${watch.web.login}") String login,
            @Value("${watch.web.password}") String senha,
            @Value("${watch.web.debug:false}") boolean debug,
            @Value("${watch.integration.id:4}") String watchIntegrationId) {
        this.seleniumConfig = seleniumConfig;
        this.login = login;
        this.senha = senha;
        this.debug = debug;
        this.watchIntegrationId = watchIntegrationId;
    }

    public WatchToken(boolean debug) {
        this.seleniumConfig = new SeleniumConfig();
        this.login = System.getenv("IXC_WEB_LOGIN");
        this.senha = System.getenv("IXC_WEB_PASSWORD");
        this.debug = debug;
        this.watchIntegrationId = System.getenv().getOrDefault("WATCH_INTEGRATION_ID", "4");
    }

    public String getToken() {
        try {
            driver = seleniumConfig.createDriver(debug);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
            taskLog.info("event=TOKEN_SELENIUM_START");
            this.logar();

            waitForIxcHomeReady();
            taskLog.info("event=TOKEN_NAV_HOME_READY");

            String token = fetchTokenFromIxcIntegration();
            taskLog.info("event=TOKEN_VALUE_FOUND");
            return token;
        } catch (WebDriverException e) {
            captureFailureEvidence(e);
            throw e;
        } finally {
            seleniumConfig.closeSession(driver);
        }
    }

    public void logar() {
        try {
            if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
                //IA: falha cedo quando a credencial nao foi configurada no ambiente.
                throw new WebDriverException("Credenciais IXC_WEB_LOGIN/IXC_WEB_PASSWORD nao configuradas");
            }

            driver.get("https://central.planetinternetprovedor.com.br/app/login");

            WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
            emailElement.sendKeys(login);

            clickWhenReady(By.id("btn-next-login"));

            WebElement passElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            passElement.sendKeys(senha);

            clickWhenReady(By.id("btn-enter-login"));
            try {
                clickWhenReady(By.id("btn-enter-login"));
            } catch (WebDriverException e) {
                log.debug("Segundo clique no botao de login nao foi necessario");
            }

            handlePostLoginOverlays();
        } catch (WebDriverException e) {
            throw e;
        }
    }

    private void waitForIxcHomeReady() {
        //IA: em headless, overlays do IXC podem permanecer sobre o menu por alguns instantes.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("layout_menu_lateral")));
        handlePostLoginOverlays();
        wait.until(driver -> isElementClickableAtCenter(By.xpath("//*[@id=\"layout_menu_lateral\"]/div/ul/li[3]/a")));
    }

    private void clickWhenReady(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollToCenter(element);

        try {
            element.click();
        } catch (ElementClickInterceptedException ex) {
            handlePostLoginOverlays();
            wait.until(driver -> isElementClickableAtCenter(locator));
            clickWithJavascript(element);
        }
    }

    private void handlePostLoginOverlays() {
        try {
            if (!driver.findElements(By.id("Auth2FA")).isEmpty()) {
                //IA: o IXC pode exibir uma tela introdutoria de 2FA antes de liberar o menu lateral.
                taskLog.info("event=TOKEN_IXC_2FA_OVERLAY_DETECTED");
                clickWhenReady(By.xpath("//*[@id=\"Auth2FA\"]/div[2]/div[3]/button[1]"));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Auth2FA")));
                taskLog.info("event=TOKEN_IXC_2FA_OVERLAY_CLOSED");
            }
        } catch (WebDriverException ex) {
            log.warn("Nao foi possivel confirmar fechamento do overlay pos-login do IXC: {}", sanitizeLogValue(ex.getMessage()));
        }
    }

    private String fetchTokenFromIxcIntegration() {
        taskLog.info("event=TOKEN_INTERNAL_ENDPOINT_SEARCH integrationId={}", watchIntegrationId);
        //IA: usa a sessao autenticada do navegador para consultar o endpoint interno que alimenta a tela de integracoes.
        WebDriverException lastError = null;

        for (int attempt = 1; attempt <= 2; attempt++) {
            try {
                taskLog.info("event=TOKEN_INTERNAL_ENDPOINT_ATTEMPT attempt={}", attempt);
                String token = wait.until(driver -> {
                    Object response = requestTokenEndpoint();
                    return extractTokenFromEndpointResponse(response);
                });
                taskLog.info("event=TOKEN_INTERNAL_ENDPOINT_OK attempt={}", attempt);
                return token;
            } catch (WebDriverException ex) {
                lastError = ex;
                taskLog.warn("event=TOKEN_INTERNAL_ENDPOINT_RETRY attempt={} error=\"{}\"", attempt, sanitizeLogValue(ex.getMessage()));
                handlePostLoginOverlays();
                pause(Duration.ofSeconds(5L));
            }
        }

        taskLog.warn("event=TOKEN_INTERNAL_ENDPOINT_FALLBACK_VISUAL error=\"{}\"", sanitizeLogValue(lastError == null ? "" : lastError.getMessage()));
        return fetchTokenFromIxcVisualFallback();
    }

    private Object requestTokenEndpoint() {
        return ((JavascriptExecutor) driver).executeAsyncScript("""
                const integrationId = arguments[0];
                const callback = arguments[arguments.length - 1];
                fetch(`/aplicativo/integracoes/action/action.php?action=recupera&id=${encodeURIComponent(integrationId)}`, {
                    credentials: 'include',
                    headers: { 'Accept': 'application/json, text/plain, */*' },
                    redirect: 'follow'
                })
                    .then(async response => {
                        const contentType = response.headers.get('content-type') || '';
                        const text = await response.text();
                        let data = text;

                        if (contentType.includes('application/json') || text.trim().startsWith('[') || text.trim().startsWith('{')) {
                            try {
                                data = JSON.parse(text);
                            } catch (error) {
                                data = text;
                            }
                        }

                        callback({
                            ok: response.ok,
                            status: response.status,
                            contentType,
                            data
                        });
                    })
                    .catch(error => callback({ ok: false, error: String(error && error.message ? error.message : error) }));
                """, watchIntegrationId);
    }

    private String extractTokenFromEndpointResponse(Object response) {
        if (!(response instanceof Map<?, ?> result)) {
            throw new WebDriverException("IXC_TOKEN_ENDPOINT_INVALID_RESPONSE " + describePayload(response));
        }

        if (!Boolean.TRUE.equals(result.get("ok"))) {
            throw new WebDriverException("IXC_TOKEN_ENDPOINT_ERROR status=" + result.get("status")
                    + " error=" + sanitizeLogValue(String.valueOf(result.get("error")))
                    + " payload=" + describePayload(result.get("data")));
        }

        Object data = result.get("data");
        if (!(data instanceof List<?> fields)) {
            throw new WebDriverException("IXC_TOKEN_ENDPOINT_INVALID_PAYLOAD status=" + result.get("status")
                    + " contentType=" + sanitizeLogValue(String.valueOf(result.get("contentType")))
                    + " payload=" + describePayload(data));
        }

        for (Object field : fields) {
            if (field instanceof Map<?, ?> item && "token_acesso_watch".equals(item.get("campo"))) {
                Object value = item.get("valor");
                return value == null || String.valueOf(value).isBlank() ? null : String.valueOf(value);
            }
        }

        throw new WebDriverException("IXC_TOKEN_FIELD_NOT_FOUND");
    }

    private String fetchTokenFromIxcVisualFallback() {
        taskLog.info("event=TOKEN_VISUAL_FALLBACK_START");
        clickWhenReady(By.xpath("//*[@id=\"layout_menu_lateral\"]/div/ul/li[3]/a"));
        taskLog.info("event=TOKEN_NAV_CONFIG_CLICKED");
        clickWhenReady(By.id("menu73573a838a837557347239b4ff197e49"));
        taskLog.info("event=TOKEN_NAV_SYSTEM_CLICKED");
        clickWhenReady(By.id("menu_item_integracoes"));
        taskLog.info("event=TOKEN_NAV_INTEGRATIONS_CLICKED");
        refreshIntegrationsGrid();
        selectWatchIntegrationRow();
        clickWhenReady(By.xpath("//*[@id=\"1_grid\"]/div/div[3]/div[1]/button[2]"));
        taskLog.info("event=TOKEN_NAV_EDIT_CLICKED");
        return waitForTokenValue();
    }

    private void refreshIntegrationsGrid() {
        //IA: fallback visual para quando o endpoint interno do IXC retorna payload inesperado.
        By refreshButton = By.cssSelector("span.pPageButtons i[title='Atualizar']");
        wait.until(ExpectedConditions.presenceOfElementLocated(refreshButton));
        clickWhenReady(refreshButton);
        taskLog.info("event=TOKEN_NAV_INTEGRATIONS_REFRESH_CLICKED");
        pause(Duration.ofSeconds(5L));
    }

    private void selectWatchIntegrationRow() {
        //IA: seleciona explicitamente a integracao WATCH para nao depender da primeira linha da grid.
        By watchRow = By.xpath("//*[@id=\"1_grid\"]//tr[.//*[normalize-space()='WATCH'] or .//*[normalize-space()='Watch']]");
        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(watchRow));
        clickWithJavascript(row);
        taskLog.info("event=TOKEN_NAV_WATCH_ROW_SELECTED");
    }

    private String waitForTokenValue() {
        //IA: o campo pode renderizar antes do IXC preencher o valor do token.
        return wait.until(driver -> {
            WebElement tokenElement = driver.findElement(By.id("token_acesso_watch"));
            String value = tokenElement.getAttribute("value");
            return value == null || value.isBlank() ? null : value;
        });
    }

    private void captureFailureEvidence(WebDriverException exception) {
        if (driver == null) {
            return;
        }

        String title = "";
        String url = "";

        try {
            title = driver.getTitle();
            url = driver.getCurrentUrl();
        } catch (WebDriverException ignored) {
        }

        log.error("Falha Selenium ao buscar token Watch. pageTitle=\"{}\" url=\"{}\" error=\"{}\"",
                sanitizeLogValue(title), sanitizeLogValue(url), sanitizeLogValue(exception.getMessage()));

        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            return;
        }

        try {
            Path directory = Path.of("logs", "selenium");
            Files.createDirectories(directory);
            Path file = directory.resolve("watch-token-error-" + LocalDateTime.now().format(SCREENSHOT_FORMATTER) + ".png");
            Files.write(file, screenshotDriver.getScreenshotAs(OutputType.BYTES));
            taskLog.error("event=TOKEN_SELENIUM_SCREENSHOT path=\"{}\"", file.toAbsolutePath());
        } catch (Exception screenshotException) {
            log.warn("Nao foi possivel salvar screenshot da falha Selenium: {}", sanitizeLogValue(screenshotException.getMessage()));
        }
    }

    private void scrollToCenter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }

    private void clickWithJavascript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void pause(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new WebDriverException("Espera interrompida ao buscar token Watch", ex);
        }
    }

    private boolean isElementClickableAtCenter(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript("""
                    const element = arguments[0];
                    const rect = element.getBoundingClientRect();
                    const x = rect.left + rect.width / 2;
                    const y = rect.top + rect.height / 2;
                    const topElement = document.elementFromPoint(x, y);
                    return topElement === element || element.contains(topElement);
                    """, element));
        } catch (WebDriverException ex) {
            return false;
        }
    }

    private String sanitizeLogValue(String value) {
        if (value == null) {
            return "";
        }
        //IA: evita quebra de linha em logs de diagnostico do Selenium.
        return value.replace("\r", " ").replace("\n", " ").replace("\"", "'");
    }

    private String describePayload(Object payload) {
        if (payload == null) {
            return "type=null";
        }

        if (payload instanceof Map<?, ?> map) {
            return "type=Map keys=" + map.keySet();
        }

        if (payload instanceof List<?> list) {
            return "type=List size=" + list.size();
        }

        if (payload instanceof String value) {
            return "type=String preview='" + preview(value) + "'";
        }

        return "type=" + payload.getClass().getSimpleName();
    }

    private String preview(String value) {
        String sanitized = sanitizeLogValue(value);
        return sanitized.length() <= 160 ? sanitized : sanitized.substring(0, 160) + "...";
    }
}
