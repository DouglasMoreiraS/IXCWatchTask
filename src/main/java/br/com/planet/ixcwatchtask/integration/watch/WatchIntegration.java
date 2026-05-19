package br.com.planet.ixcwatchtask.integration.watch;

import br.com.planet.ixcwatchtask.exception.IntegrationException;
import br.com.planet.ixcwatchtask.exception.TicketNaoEncontradoException;
import br.com.planet.ixcwatchtask.exception.TokenInvalidoException;
import br.com.planet.ixcwatchtask.integration.IntegrationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@Service
public class WatchIntegration {

    private RestTemplate restTemplate;

    @Value("${watch.api.delete-url}")
    private String urlDeletar;

    @Value("${watch.api.search-url}")
    private String urlBuscar;

    public WatchIntegration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public IntegrationResponse deleteTicket(String token, String ticket) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(urlDeletar)
                    .queryParam("pTicket", ticket)
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<WatchDeleteResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    WatchDeleteResponse.class
            );

            WatchDeleteResponse responseBody = response.getBody();

            if (responseBody != null) {
                if (!responseBody.getHasError()) {
                    return new IntegrationResponse(true, "Deletado com sucesso");
                } else {
                    return new IntegrationResponse(false, responseBody.getErrorMessage());
                }
            }

            return new IntegrationResponse(false, "Body null");

        } catch (HttpStatusCodeException e) {

            HttpStatusCode status = e.getStatusCode();

            if (status.value() == 401) {
                throw new TokenInvalidoException("Token inválido ou expirado");

            } else if (status.value() == 400) {

                String body = new String(e.getResponseBodyAsByteArray(), StandardCharsets.UTF_8);

                if (body.contains("Ticket nao encontrado")) {
                    throw new TicketNaoEncontradoException("Ticket não encontrado");
                }

                throw new IntegrationException("Erro 400: " + body);
            }

            throw new IntegrationException("Erro HTTP: " + status);
        }
    }

    public WatchResponse buscarTicket(String token, String pacote, String idIntegracao) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(urlBuscar)
                    .queryParam("pPacote", pacote)
                    .queryParam("pAssinanteIDIntegracao", idIntegracao)
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<WatchResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            request,
                            WatchResponse.class
                    );
            WatchResponse body = response.getBody();
            if (body != null && !body.getHasError()) {
                return body;
            }
            return null;


        } catch (HttpStatusCodeException e) {

            HttpStatusCode status = e.getStatusCode();

            if (status.value() == 401) {
                throw new TokenInvalidoException("Token inválido ou expirado");

            } else if (status.value() == 400) {

                String body = new String(e.getResponseBodyAsByteArray(), StandardCharsets.UTF_8);

                if (body.contains("Ticket nao encontrado")) {
                    throw new TicketNaoEncontradoException("Ticket não encontrado");
                }

                throw new IntegrationException("Erro 400: " + body);
            }

            throw new IntegrationException("Erro HTTP: " + status);
        }
    }


}

