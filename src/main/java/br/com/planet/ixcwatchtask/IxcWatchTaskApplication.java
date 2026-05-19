package br.com.planet.ixcwatchtask;

import br.com.planet.ixcwatchtask.config.SeleniumConfig;
import br.com.planet.ixcwatchtask.task.MainTask;
import br.com.planet.ixcwatchtask.token.WatchToken;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling //IA: habilita a execucao dos metodos anotados com @Scheduled.
public class IxcWatchTaskApplication {

    @Autowired
    private MainTask mainTask;

    private static Logger log = LoggerFactory.getLogger(IxcWatchTaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IxcWatchTaskApplication.class, args);


    }
}
