package br.com.planet.ixcwatchtask.token;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class WatchTokenTest {

    public void getToken(){

        WatchToken token = new WatchToken(false);
        System.out.println(token.getToken());

    }

}
