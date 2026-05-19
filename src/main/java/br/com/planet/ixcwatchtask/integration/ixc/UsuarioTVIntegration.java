package br.com.planet.ixcwatchtask.integration.ixc;

import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;

@Service
public class UsuarioTVIntegration extends IXCIntegration<UsuarioTVResponse>{

    private static final String URL = "tv_usuarios";

    public UsuarioTVIntegration(RestTemplate restTemplate) {
        super(restTemplate, URL,UsuarioTVResponse.class);
    }

    @Override
    protected String getQtype(){
        return "tv_usuarios.id";
    }

    public UsuarioTVDeleteResponse deletar(Long id){
        String tempURL = getUrl() + "/" + id;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", getAuthHeader());
           // headers.set(HEADER_NAME, HEADER_TYPE);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    tempURL,
                    HttpMethod.DELETE,
                    request,
                    String.class
            );

            ObjectMapper mapper = new ObjectMapper();

            UsuarioTVDeleteResponse body = mapper.readValue(
                    response.getBody(),
                    UsuarioTVDeleteResponse.class
            );


            return body;
        } catch (Exception e) {
            throw e;
        }

    }

}
