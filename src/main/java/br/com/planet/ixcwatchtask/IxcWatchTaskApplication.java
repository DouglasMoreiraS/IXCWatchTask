package br.com.planet.ixcwatchtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling //IA: habilita a execucao dos metodos anotados com @Scheduled.
public class IxcWatchTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(IxcWatchTaskApplication.class, args);
    }
}
