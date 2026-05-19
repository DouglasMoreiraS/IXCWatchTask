package br.com.planet.ixcwatchtask.token;

import br.com.planet.ixcwatchtask.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class WatchToken {

    SeleniumConfig seleniumConfig;
    WebDriver driver;
    WebDriverWait wait;
    private final String login;
    private final String senha;
    private final boolean debug;

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
            wait = new WebDriverWait(driver, Duration.ofSeconds(5L));
            this.logar();

            driver.findElement(By.xpath("//*[@id=\"layout_menu_lateral\"]/div/ul/li[3]/a")).click(); //Menu configurações
            driver.findElement(By.id("menu73573a838a837557347239b4ff197e49")).click(); //Menu integrações
            driver.findElement(By.id("menu_item_integracoes")).click(); //Sub menu integrações TV

            wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id=\"1_grid\"]/div/div[3]/div[1]/button[2]")))).click(); //Botão "editar" em menu Integrações TV (Watch é o primeiro da lista e já aparece selecionado)

            String token = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("token_acesso_watch")))).getAttribute("value"); //Campo Token Acesso
            return token;

        } catch (WebDriverException e) {
            throw e;
        }finally {
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

            driver.findElement(By.id("btn-next-login")).click();

            WebElement passElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            passElement.sendKeys(senha);

            driver.findElement(By.id("btn-enter-login")).click();
            try {//Caso dê erro de sessão ativa, ele clica novamente no botão de login e faz o by-pass
                driver.findElement(By.id("btn-enter-login")).click();
            } catch (WebDriverException e) {
            }

            try { //Verificando se mensagem de autenticação de 2 fatores irá aparecer
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Auth2FA")));
                driver.findElement(By.xpath("//*[@id=\"Auth2FA\"]/div[2]/div[3]/button[1]")).click();
            } catch (WebDriverException ex) {
            }

        } catch (WebDriverException e) {
            throw e;
        }
    }


}

