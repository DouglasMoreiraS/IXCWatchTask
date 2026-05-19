package br.com.planet.ixcwatchtask.service;

import br.com.planet.ixcwatchtask.exception.TokenInvalidoException;
import br.com.planet.ixcwatchtask.integration.IntegrationResponse;
import br.com.planet.ixcwatchtask.integration.watch.WatchDeleteResponse;
import br.com.planet.ixcwatchtask.integration.watch.WatchIntegration;
import br.com.planet.ixcwatchtask.integration.watch.WatchResponse;
import br.com.planet.ixcwatchtask.model.ConsultaResponse;
import br.com.planet.ixcwatchtask.model.ixc.Contrato;
import br.com.planet.ixcwatchtask.model.watch.Ticket;
import br.com.planet.ixcwatchtask.token.WatchToken;
import org.apache.tomcat.util.bcel.classfile.ConstantUtf8;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WatchServiceTest {

    @Autowired
    WatchService watchService;
    @Autowired
    ContratoIXCService ixcService;


    @Test
    public void testAPI() {
        System.out.println("Iniciando teste");
        System.out.println("Buscando Token no IXCSoft...");

        String token = new WatchToken(true).getToken();

        if (token == null || token.isEmpty()) {
            token = new WatchToken(true).getToken();
            if (token == null || token.isEmpty()) {
                System.out.println("Erro ao resgatar token");
                return;
            }
        }

        System.out.println("Token encontrado: " + token);

        System.out.println("Listando contratos bloqueados no IXCSoft...");

        List<Contrato> contratosBloqueados = new ArrayList<>();
        contratosBloqueados = ixcService.listarAPI();

        System.out.println("Foram encontrados: " + contratosBloqueados.size() + " contratos");
        System.out.println("Iniciando verificação/exclusão dos contratos");

        List<Ticket> ticketsUp = new ArrayList<>();
        List<Ticket> ticketsHBO = new ArrayList<>();

        List<ConsultaResponse> consultaResponses = new ArrayList<>();

        for (Contrato c : contratosBloqueados) {

            System.out.println("Verificação contrato " + c.getId());
            try {
                Ticket ticketUp = watchService.buscarTicketUp(Long.toString(c.getId()), token);
                Ticket ticketHBO = watchService.buscarTicketHBO(Long.toString(c.getId()), token);
                if (ticketUp != null) {
                    System.out.println("Ticket UP encontrado: " + ticketUp.getTicket());
                    ticketsUp.add(ticketUp);
                    consultaResponses.add(new ConsultaResponse(true, Long.toString(c.getId()), ticketUp.getTicket(), "3939", ""));
                } else {
                    consultaResponses.add(new ConsultaResponse(false, Long.toString(c.getId()), "", "3939", "TicketUp não encontrado"));
                    System.out.println("TicketUp não encontrado para " + c.getId());
                }

                if (ticketHBO != null) {
                    System.out.println("TicketHBO encontrado: " + ticketHBO.getTicket());
                    ticketsHBO.add(ticketHBO);
                    consultaResponses.add(new ConsultaResponse(true, Long.toString(c.getId()), ticketHBO.getTicket(), "3940", ""));
                } else {
                    System.out.println("TicketHBO não encontrado para " + c.getId());
                    consultaResponses.add(new ConsultaResponse(false, Long.toString(c.getId()), "", "3940", "TicketHBO não encontrado"));
                }

            } catch (TokenInvalidoException e) {

                System.out.println("Operação interrompida, token invalido ou expirado");
                break;
            } catch (RuntimeException ex) {
                consultaResponses.add(new ConsultaResponse(false, Long.toString(c.getId()),"", "", "Erro ao consultar ticket: " + ex.getMessage()));
                ex.printStackTrace();
                System.out.println("Erro ao verificar ticket: " + ex.getMessage());
                System.out.println();
            }
        }
        System.out.println("Consultas: ");

        for (ConsultaResponse cR : consultaResponses){
            System.out.println(cR.toString());
        }
        System.out.println("Verificação de acessos encerrada");
        System.out.println("Tickets UP encontrados: " + ticketsUp.size());
        System.out.println("Tickets HBO encontrados: " + ticketsHBO.size());


    }


}
