package br.com.planet.ixcwatchtask.service;

import br.com.planet.ixcwatchtask.exception.IntegrationException;
import br.com.planet.ixcwatchtask.exception.TicketNaoEncontradoException;
import br.com.planet.ixcwatchtask.exception.TokenInvalidoException;
import br.com.planet.ixcwatchtask.integration.IntegrationResponse;
import br.com.planet.ixcwatchtask.integration.watch.WatchIntegration;
import br.com.planet.ixcwatchtask.integration.watch.WatchResponse;
import br.com.planet.ixcwatchtask.model.watch.Ticket;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class WatchService {

    private final WatchIntegration integration;

    public WatchService(WatchIntegration integration) {
        this.integration = integration;
    }

    public Ticket buscarTicketUp(String id, String token) throws TicketNaoEncontradoException, TokenInvalidoException, IntegrationException {
        try {
            return buscarTicket(id, token, "3939");
        } catch (TicketNaoEncontradoException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Ticket buscarTicketHBO(String id, String token) throws TicketNaoEncontradoException, TokenInvalidoException, IntegrationException {
        try {
            return buscarTicket(id, token, "3940");
        } catch (TicketNaoEncontradoException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Ticket buscarTicketPremiere(String id, String token) throws TicketNaoEncontradoException, TokenInvalidoException, IntegrationException {
        try {
            return buscarTicket(id, token, "3944");
        } catch (TicketNaoEncontradoException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Ticket buscarTicketHubPremium(String id, String token) throws TicketNaoEncontradoException, TokenInvalidoException, IntegrationException {
        try {
            return buscarTicket(id, token, "15816");
        } catch (TicketNaoEncontradoException e) {
            return null;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Ticket buscar(String idContrato, String token) {
        return Stream.<Supplier<Ticket>>of(
                        () -> buscarTicketUp(idContrato, token),
                        () -> buscarTicketHBO(idContrato, token),
                        () -> buscarTicketPremiere(idContrato, token),
                        () -> buscarTicketHubPremium(idContrato, token)
                )
                .map(supplier -> {
                    try {
                        return supplier.get();
                    } catch (TicketNaoEncontradoException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    protected Ticket buscarTicket(String id, String token, String pacote) throws TicketNaoEncontradoException, TokenInvalidoException, IntegrationException {
        return parseResponse(integration.buscarTicket(token, pacote, id));
    }

    public IntegrationResponse deletar(String token, String ticket) {
        return integration.deleteTicket(token, ticket);
    }


    public Ticket parseResponse(WatchResponse response) {

        //IA: a API pode retornar sucesso HTTP com Result/list nulos; nesse caso tratamos como nao localizado.
        if (response == null || response.getResult() == null || response.getResult().getList() == null || response.getResult().getList().isEmpty())
            return null;

        WatchResponse.Item item = response.getResult().getList().get(0);

        Ticket ticket = new Ticket();
        ticket.setTicket(item.getTicket());
        ticket.setPacote(item.getPacote() == null ? "" : Integer.toString(item.getPacote()));
        ticket.setStatus(String.valueOf(item.getStatus()));
        return ticket;
    }


}
