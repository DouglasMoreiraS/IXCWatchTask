package br.com.planet.ixcwatchtask.service;


import br.com.planet.ixcwatchtask.exception.TicketNaoEncontradoException;
import br.com.planet.ixcwatchtask.integration.IntegrationResponse;
import br.com.planet.ixcwatchtask.integration.ixc.UsuarioTVDeleteResponse;
import br.com.planet.ixcwatchtask.model.ixc.Contrato;
import br.com.planet.ixcwatchtask.model.ixc.UsuarioTV;
import br.com.planet.ixcwatchtask.model.watch.Ticket;
import br.com.planet.ixcwatchtask.token.WatchToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class UsuarioTVService {

    @Autowired
    UsuarioTVIXCService usuarioIXCService;
    @Autowired
    ContratoIXCService contratoService;
    @Autowired
    WatchService watchService;

    @Test
    public void testAPI() {

      //IA: nunca manter tokens reais ou exemplos de JWT no codigo versionado.
        String token = new WatchToken(true).getToken();
        if (token == null || token.isEmpty() || token.isBlank()){
            System.err.println("Retorno de token foi invalido");
            return;
        }
        System.out.println(token);

        List<UsuarioTV> usuarios = usuarioIXCService.listarAPI();
        List<Contrato> contratos = contratoService.listarAPI();

        int watchUpDel = 0;
        int hboDel = 0;
        int premiereDel = 0;
        int hubDel = 0;
        int ixcDel = 0;

        int ticketNotFound = 0;

        int watchIntegrationError = 0;
        int ixcIntegrationError = 0;

        Map<Long, Contrato> contratosMap = contratos.stream()
                .collect(Collectors.toMap(Contrato::getId, c -> c));

        List<UsuarioTV> usuariosCancelar = new ArrayList<>();

        for (UsuarioTV u : usuarios) {
            if (u.getContratoId() != null && u.getContratoId() != 0) {
                Long idContrato = u.getContratoId();

                Contrato contrato = contratosMap.get(idContrato);

                if (contrato != null) {

                    System.out.println("Identificando Ticket: ");

                    Ticket temp = watchService.buscar(Long.toString(u.getContratoId()), token);

                    if (temp == null) {
                        System.err.println("Ticket " + u.getTicketWatch() + " atrelado ao contrato " + u.getContratoId() + " não foi encontrado na plataforma da Watch");
                        ticketNotFound++;
                        continue;
                    }

                    System.out.println("========================");
                    System.out.println("Contrato: " + idContrato);
                    System.out.println("Status do contrato: " + contrato.getStatusAcesso());
                    System.out.println("Login: " + u.getLogin());
                    System.out.println("Ticket: " + u.getTicketWatch());
                    System.out.println("Status do ticket: " + u.getStatusTicket());
                    System.out.println("Pacote do ticket: " + temp.getPacote());
                    System.out.println("Status do ticket na plataforma Watch: " + temp.getStatus());
                    System.out.println("========================");


                    System.out.println("Deletando Ticket WatchTV");
                    try {
                        IntegrationResponse responseWatch = watchService.deletar(token, temp.getTicket());
                        if (responseWatch != null) {
                            System.out.println(responseWatch.getMessage());
                        } else
                            System.out.println("Acesso " + u.getTicketWatch() + " deletado com sucesso");
                        ixcDel++;
                        switch (temp.getPacote()) {

                            case Ticket.PACOTE_UP -> {
                                watchUpDel++;
                            }

                            case Ticket.PACOTE_HBO -> {
                                hboDel++;
                            }
                            case Ticket.PACOTE_PREMIERE -> {
                                premiereDel++;
                            }

                            case Ticket.PACOTE_HUB_PREMIUM -> {
                                hubDel++;
                            }
                        }

                    } catch (TicketNaoEncontradoException e) {
                        System.err.println("Ticket " + temp.getTicket() + " não encontrado na Watch");
                        ticketNotFound++;
                    } catch (RuntimeException ex) {
                        System.err.println("Erro ao tentar apagar ticket watch:" + ex.getMessage());
                        watchIntegrationError++;
                    }

                    System.out.println("Deletando Usuario TV IXCSoft");

                    try {
                        UsuarioTVDeleteResponse response = usuarioIXCService.deletar(u);
                        if (response != null)
                            System.out.println(response.getMessage());
                    } catch (RuntimeException ex) {
                        System.err.println("Erro ao deletar usuario TV IXCSoft: " + ex.getMessage());
                        ixcIntegrationError++;
                    }
                }
            } else {
                System.err.println("Usuario TV id: " + u.getId() + " não possui contrato vinculado");
            }
        }

       int watchTicketTotal = watchUpDel + hboDel + hubDel + premiereDel;


        System.out.println("Acessos IXC Deletados: " + ixcDel + "\n" +
                "Acessos Watch Up deletados: " + watchUpDel + "\n" +
                "Acessos HBO deletados: " + hboDel + "\n" +
                "Acessos Premiere deletados: " + premiereDel + "\n" +
                "Acessos Hub Premium deletados " + hubDel + "\n" +
                "Total acessos Watch deletados: " + watchTicketTotal + "\n" +
                "Acessos Watch não encontrados: " + ticketNotFound + "\n" +
                "Erros integração watch: " + watchIntegrationError + "\n" +
                "Erros integração IXC: " + ixcIntegrationError);

    }
}
