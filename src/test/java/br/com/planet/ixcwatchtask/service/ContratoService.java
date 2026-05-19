package br.com.planet.ixcwatchtask.service;

import br.com.planet.ixcwatchtask.model.ixc.Contrato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContratoService {

    @Autowired
    ContratoIXCService service;


    @Test
    public void testarAPI(){

        List<Contrato> contratos = service.listarAPI();

        System.out.println("Retorno de contratos: " + contratos.size());


    }

}
