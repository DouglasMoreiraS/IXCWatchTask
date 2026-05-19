package br.com.planet.ixcwatchtask.integration.ixc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContratoIntegration extends IXCIntegration<ContratoResponse>{

    private static final String URL = "/cliente_contrato";

    public ContratoIntegration(RestTemplate restTemplate) {
        super(restTemplate, URL,ContratoResponse.class);
    }

    @Override
    protected String getQtype(){
        return "cliente_contrato.id";
    }


}
