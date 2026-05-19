package br.com.planet.ixcwatchtask.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Component
public class SeleniumConfig {

    @Value("${selenium.chrome.driver-path}")
    private String driverPathConfig = "C:\\ixcwatchtask\\driver\\chromedriver.exe";

    @Value("${selenium.chrome.binary-path}")
    private String chromiumPathConfig = "C:\\ixcwatchtask\\browser\\chrome-win\\chrome.exe";

    public WebDriver createDriver(boolean debug) {

        Path driverPath = Paths.get(driverPathConfig);
        Path chromiumPath = Paths.get(chromiumPathConfig);

        System.setProperty("webdriver.chrome.driver", driverPath.toString());

        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromiumPath.toString());

        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-component-update");

        if (!debug) {
            options.addArguments("--headless=new");
        }

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public void closeSession(WebDriver driver) {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (RuntimeException var2) {
        }
    }

}
