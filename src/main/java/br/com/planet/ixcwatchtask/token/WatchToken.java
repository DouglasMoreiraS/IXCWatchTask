package br.com.planet.ixcwatchtask.token;

import br.com.planet.ixcwatchtask.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class WatchToken {

    private final SeleniumConfig seleniumConfig;
    private WebDriver driver;
    private WebDriverWait wait;
    private final String login;
    private final String senha;
    private final boolean debug;

    @Autowired
    public WatchToken(
            SeleniumConfig seleniumConfig,
            @Value("${watch.web.login}") String login,
            @Value("${watch.web.password}") String senha,
            @Value("${watch.web.debug:false}") boolean debug) {
        this.seleniumConfig = seleniumConfig;
        this.login = login;
        this.senha = senha;
        this.debug = debug;
    }

    public WatchToken(boolean debug) {
        this.seleniumConfig = new SeleniumConfig();
        this.login = System.getenv("IXC_WEB_LOGIN");
        this.senha = System.getenv("IXC_WEB_PASSWORD");
        this.debug = debug;
    }

    public String getToken() {
        try {
            driver = seleniumConfig.createDriver(debug);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
            this.logar();

            waitForIxcHomeReady();
            clickWhenReady(By.xpath("//*[@id=\"layout_menu_lateral\"]/div/ul/li[3]/a"));
            clickWhenReady(By.id("menu73573a838a837557347239b4ff197e49"));
            clickWhenReady(By.id("menu_item_integracoes"));
            clickWhenReady(By.xpath("//*[@id=\"1_grid\"]/div/div[3]/div[1]/button[2]"));

            return wait.until(ExpectedConditions.presenceOfElementLocated(By.id("token_acesso_watch")))
                    .getAttribute("value");
        } catch (WebDriverException e) {
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
            }

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Auth2FA")));
                clickWhenReady(By.xpath("//*[@id=\"Auth2FA\"]/div[2]/div[3]/button[1]"));
            } catch (WebDriverException ex) {
            }
        } catch (WebDriverException e) {
            throw e;
        }
    }

    private void waitForIxcHomeReady() {
        //IA: em headless, overlays do IXC podem permanecer sobre o menu por alguns instantes.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("layout_menu_lateral")));
        wait.until(driver -> isElementClickableAtCenter(By.xpath("//*[@id=\"layout_menu_lateral\"]/div/ul/li[3]/a")));
    }

    private void clickWhenReady(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollToCenter(element);

        try {
            element.click();
        } catch (ElementClickInterceptedException ex) {
            wait.until(driver -> isElementClickableAtCenter(locator));
            clickWithJavascript(element);
        }
    }

    private void scrollToCenter(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }

    private void clickWithJavascript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
}
