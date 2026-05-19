package br.com.planet.ixcwatchtask.integration.ixc;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public abstract class IXCIntegration<T> {


    protected RestTemplate restTemplate;
    protected String url;

    @Value("${ixc.api.auth}")
    protected String authStr;

    @Value("${ixc.api.base-url}")
    protected String baseUrl;

    protected static final String HEADER_NAME = "ixcsoft";
    protected static final String HEADER_TYPE = "listar";

    protected ResponseEntity<String> response;
    protected final Class<T> type;
    private final String resourceUrl;

    public IXCIntegration(RestTemplate restTemplate, String url, Class<T> type) {
        this.restTemplate = restTemplate;
        this.resourceUrl = url;
        this.type = type;
    }

    protected String getUrl() {
        if (url == null) {
            //IA: monta a URL depois da injecao das propriedades pelo Spring.
            url = baseUrl.endsWith("/") ? baseUrl + resourceUrl : baseUrl + "/" + resourceUrl;
        }
        return url;
    }

    protected String getAuthHeader() {
        if (authStr == null || authStr.isBlank()) {
            //IA: falha cedo para nao executar chamadas externas sem credencial explicita.
            throw new IllegalStateException("Propriedade ixc.api.auth/IXC_AUTH nao configurada");
        }
        String encodedAuth = Base64.getEncoder().encodeToString(authStr.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedAuth;
    }

    protected void fazerRequisicao(String jsonBody) {

        try {
            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", getAuthHeader());
            headers.set(HEADER_NAME, HEADER_TYPE);

            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            // Fazer a requisição
            response = restTemplate.exchange(
                    getUrl(),
                    HttpMethod.POST,
                    request,
                    String.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer requisição API IXCSoft: ", e);
        }
    }

    public  ResponseEntity<String> testarApi() {

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", getAuthHeader());
        headers.set(HEADER_NAME, HEADER_TYPE);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            response = restTemplate.exchange(
                    getUrl(),
                    HttpMethod.HEAD,
                    request,
                    String.class
            );
            return ResponseEntity.ok("API externa OK! Status: " + response.getStatusCode());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Falha ao testar API externa: " + e.getMessage());
        }
    }

    /**
     * Monta o corpo JSON padrão para consultas na API.
     *
     * @param qtype valor para o campo qtype e sortname
     * @param gridParams parâmetros para filtro grid_param (já formatados)
     * @return JSON montado como String
     */
    protected String montarJsonBody(String gridParams) {
        return String.format("""
            {
              "qtype": "%s",
              "query": "1",
              "oper": ">=",
              "page": "1",
              "rp": "50000",
              "sortname": "%s",
              "sortorder": "desc",
              "grid_param": "[%s]"
            }
        """, getQtype(), getQtype(), gridParams);
    }

    protected abstract String getQtype();

    public T listar(){
        String jsonBody = montarJsonBody("");
        this.fazerRequisicao(jsonBody);
        return this.parseJson(type);

    }
    public T listar(String gridParam){
        String jsonBody = montarJsonBody(gridParam);
        this.fazerRequisicao(jsonBody);
        return this.parseJson(type);
    }

    protected <T> T parseJson(Class<T> clazz) {
        try {
            ObjectMapper objMapper = new ObjectMapper();
            return objMapper.readValue(response.getBody(), clazz);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
