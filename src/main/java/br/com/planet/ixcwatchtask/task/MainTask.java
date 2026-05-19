package br.com.planet.ixcwatchtask.task;

import br.com.planet.ixcwatchtask.exception.TicketNaoEncontradoException;
import br.com.planet.ixcwatchtask.integration.IntegrationResponse;
import br.com.planet.ixcwatchtask.integration.ixc.UsuarioTVDeleteResponse;
import br.com.planet.ixcwatchtask.model.ixc.Contrato;
import br.com.planet.ixcwatchtask.model.ixc.UsuarioTV;
import br.com.planet.ixcwatchtask.model.watch.Ticket;
import br.com.planet.ixcwatchtask.service.ContratoIXCService;
import br.com.planet.ixcwatchtask.service.UsuarioTVIXCService;
import br.com.planet.ixcwatchtask.service.WatchService;
import br.com.planet.ixcwatchtask.token.WatchToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MainTask {

    private static final Logger log = LoggerFactory.getLogger(MainTask.class);
    private static final Logger taskLog = LoggerFactory.getLogger("IXC_WATCH_TASK");
    private static final DateTimeFormatter EXECUTION_ID_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final UsuarioTVIXCService usuarioIXCService;
    private final ContratoIXCService contratoService;
    private final WatchService watchService;
    private final WatchToken watchToken;

    public MainTask(UsuarioTVIXCService usuarioIXCService, ContratoIXCService contratoService, WatchService watchService, WatchToken watchToken) {
        this.usuarioIXCService = usuarioIXCService;
        this.contratoService = contratoService;
        this.watchService = watchService;
        this.watchToken = watchToken;
    }

    @Scheduled(cron = "0 00 12 * * *") //Aplica todo dia as 12h
    public void mainTask() {
        //IA: identificador unico da execucao para correlacionar eventos no arquivo logs/task.
        String executionId = LocalDateTime.now().format(EXECUTION_ID_FORMATTER);

        int watchUpDel = 0;
        int hboDel = 0;
        int premiereDel = 0;
        int hubDel = 0;
        int ixcDel = 0;

        int ticketNotFound = 0;

        int watchIntegrationError = 0;
        int ixcIntegrationError = 0;

        //Lista de falhas/inconsistências encontradas no processo
        List<UsuarioTV> usuariosTVSemContrato = new ArrayList<>();
        List<UsuarioTV> naoLocalizadoWatch = new ArrayList<>();
        List<UsuarioTV> deleteFailureWatch = new ArrayList<>();
        List<UsuarioTV> deleteFailureIXC = new ArrayList<>();

        log.info("Iniciando task IXC x Watch. executionId={}", executionId);
        taskLog.info("event=EXECUTION_START executionId={}", executionId);
        taskLog.info("event=TOKEN_SEARCH executionId={}", executionId);

        String token = watchToken.getToken();

        if (token == null || token.isBlank()) {
            log.error("O token retornado é invalido, encerrando tarefa");
            taskLog.error("event=EXECUTION_ABORT executionId={} reason=TOKEN_INVALID", executionId);
            return;
        }

        //IA: nao registrar o token em log para reduzir risco de vazamento de credencial.
        log.info("Token Watch localizado com sucesso");
        taskLog.info("event=TOKEN_OK executionId={}", executionId);
        log.info("Iniciando busca de contratos em bloqueio");

        List<UsuarioTV> usuarios = usuarioIXCService.listarAPI();
        List<Contrato> contratos = contratoService.listarAPI();

        log.info("Contratos em bloqueio localizados: {}", contratos.size());
        taskLog.info("event=IXC_QUERY_OK executionId={} usuariosTv={} contratosBloqueados={}", executionId, usuarios.size(), contratos.size());

        Map<Long, Contrato> contratosMap = contratos.stream()
                //IA: mantem a rotina rodando mesmo se o IXC retornar o mesmo contrato em mais de um filtro.
                .collect(Collectors.toMap(Contrato::getId, c -> c, (contratoAtual, contratoDuplicado) -> contratoAtual));

        for (UsuarioTV u : usuarios) {
            if (u.getContratoId() == null || u.getContratoId() == 0) {
                log.warn("Usuario TV id: {} não possui contrato vinculado", u.getId());
                taskLog.warn("event=USER_SKIPPED_NO_CONTRACT executionId={} usuarioTvId={}", executionId, u.getId());
                usuariosTVSemContrato.add(u);
                continue;
            }

            Long idContrato = u.getContratoId();

            Contrato contrato = contratosMap.get(idContrato);

            if (contrato == null) //Se não encontrar nenhum contrato na lista puxada do IXC, signfica que o contrato está ativo ou cancelado
                continue;

            log.info("Localizando ticket na plataforma da Watch. contratoId={} usuarioTvId={}", idContrato, u.getId());
            taskLog.info("event=WATCH_TICKET_SEARCH executionId={} contratoId={} usuarioTvId={}", executionId, idContrato, u.getId());

            Ticket temp = watchService.buscar(Long.toString(u.getContratoId()), token);

            if (temp == null) {
                log.error("Ticket {} atrelado ao contrato {} não foi encontrado na plataforma da Watch", u.getTicketWatch(), u.getContratoId());
                taskLog.warn("event=WATCH_TICKET_NOT_FOUND executionId={} contratoId={} usuarioTvId={}", executionId, u.getContratoId(), u.getId());
                ticketNotFound++;
                naoLocalizadoWatch.add(u);
                continue;
            }

            taskLog.info("event=WATCH_TICKET_FOUND executionId={} contratoId={} usuarioTvId={} pacote={} statusWatch={}", executionId, idContrato, u.getId(), temp.getPacote(), temp.getStatus());

            log.info("""
                     \n========================
                    Contrato: {}
                    Status do contrato: {}\s
                     \
                    Login: {}\s
                    Ticket: {}\s
                     \
                    Status do ticket: {}\s
                    Pacote do ticket:{}\s
                    Status do ticket na plataforma Watch: {}\s
                    ========================""", idContrato, contrato.getStatusAcesso(), u.getLogin(), u.getTicketWatch(), u.getStatusTicket(), temp.getPacote(), temp.getStatus());


            log.info("Deletando Ticket WatchTV. contratoId={} usuarioTvId={} pacote={}", idContrato, u.getId(), temp.getPacote());
            taskLog.info("event=WATCH_DELETE_START executionId={} contratoId={} usuarioTvId={} pacote={}", executionId, idContrato, u.getId(), temp.getPacote());

            try {
                IntegrationResponse responseWatch = watchService.deletar(token, temp.getTicket());
                if (responseWatch != null) {
                    if (!responseWatch.isSucess()) {
                        log.warn(responseWatch.getMessage());
                        taskLog.warn("event=WATCH_DELETE_FAILED executionId={} contratoId={} usuarioTvId={} message=\"{}\"", executionId, idContrato, u.getId(), sanitizeLogValue(responseWatch.getMessage()));
                        watchIntegrationError++;
                    } else {
                        log.info("Acesso {} deletado com sucesso na Watch", u.getTicketWatch());
                        taskLog.info("event=WATCH_DELETE_SUCCESS executionId={} contratoId={} usuarioTvId={} pacote={}", executionId, idContrato, u.getId(), temp.getPacote());
                    }
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
                }
            } catch (TicketNaoEncontradoException e) {
                log.warn("Ticket {} não encontrado na Watch", temp.getTicket());
                taskLog.warn("event=WATCH_DELETE_TICKET_NOT_FOUND executionId={} contratoId={} usuarioTvId={}", executionId, idContrato, u.getId());
                naoLocalizadoWatch.add(u);
                ticketNotFound++;
            } catch (RuntimeException ex) {
                deleteFailureWatch.add(u);
                log.error("Erro ao tentar apagar ticket watch:{}", ex.getMessage());
                taskLog.error("event=WATCH_DELETE_ERROR executionId={} contratoId={} usuarioTvId={} error=\"{}\"", executionId, idContrato, u.getId(), sanitizeLogValue(ex.getMessage()));
                watchIntegrationError++;
            }

            log.info("Deletando Usuario TV IXCSoft. contratoId={} usuarioTvId={}", idContrato, u.getId());
            taskLog.info("event=IXC_DELETE_START executionId={} contratoId={} usuarioTvId={}", executionId, idContrato, u.getId());

            try {
                UsuarioTVDeleteResponse response = usuarioIXCService.deletar(u);
                if (response != null)
                    log.warn(response.getMessage());

                log.info("Usuario TV {} deletado com sucesso", u.getId());
                taskLog.info("event=IXC_DELETE_SUCCESS executionId={} contratoId={} usuarioTvId={}", executionId, idContrato, u.getId());
                ixcDel++;

            } catch (RuntimeException ex) {
                deleteFailureIXC.add(u);
                log.error("Erro ao deletar usuario TV IXCSoft: {}", ex.getMessage());
                taskLog.error("event=IXC_DELETE_ERROR executionId={} contratoId={} usuarioTvId={} error=\"{}\"", executionId, idContrato, u.getId(), sanitizeLogValue(ex.getMessage()));
                ixcIntegrationError++;
            }
        }

        int watchTicketTotal = watchUpDel + hboDel + hubDel + premiereDel;


        log.info("Acessos IXC Deletados: {}\nAcessos Watch Up deletados: {}\nAcessos HBO deletados: {}\nAcessos Premiere deletados: {}\nAcessos Hub Premium deletados {}\nTotal acessos Watch deletados: {}\nAcessos Watch não encontrados: {}\nErros integração watch: {}\nErros integração IXC: {}", ixcDel, watchUpDel, hboDel, premiereDel, hubDel, watchTicketTotal, ticketNotFound, watchIntegrationError, ixcIntegrationError);
        taskLog.info("event=EXECUTION_SUMMARY executionId={} ixcDeleted={} watchUpDeleted={} hboDeleted={} premiereDeleted={} hubDeleted={} watchDeletedTotal={} watchNotFound={} watchErrors={} ixcErrors={}", executionId, ixcDel, watchUpDel, hboDel, premiereDel, hubDel, watchTicketTotal, ticketNotFound, watchIntegrationError, ixcIntegrationError);


        String inconsistencias = "==Inconsistências==\n";

        inconsistencias = inconsistencias.concat("\n-TV sem contratos vinculados: " + usuariosTVSemContrato.size());

        for (UsuarioTV usuarioTV : usuariosTVSemContrato) {
            inconsistencias = inconsistencias.concat("\n-Usuario TV ID: " + usuarioTV.getId());
        }

        inconsistencias = inconsistencias.concat("\n-Usuarios TV não encontrados na plataforma da Watch TV: " + naoLocalizadoWatch.size());

        inconsistencias = inconsistencias.concat("\n-Falhas em delete na integração Watch: " + deleteFailureWatch.size());

        for (UsuarioTV usuarioTV : deleteFailureWatch) {
            inconsistencias = inconsistencias.concat("\n-Usuario TV ID: " + usuarioTV.getId());
        }

        inconsistencias = inconsistencias.concat("\n-Falhas em delete na integração IXC: " + deleteFailureIXC.size());

        for (UsuarioTV usuarioTV : deleteFailureIXC) {
            inconsistencias = inconsistencias.concat("\n-Usuario TV ID" + usuarioTV.getId());
        }

        log.warn("{}", inconsistencias);
        taskLog.warn("event=INCONSISTENCY_SUMMARY executionId={} usuariosSemContrato={} naoLocalizadoWatch={} deleteFailureWatch={} deleteFailureIXC={}", executionId, usuariosTVSemContrato.size(), naoLocalizadoWatch.size(), deleteFailureWatch.size(), deleteFailureIXC.size());

        log.info("\n==Ajuste de inconsistencias==");

        if (naoLocalizadoWatch.isEmpty()) {
            log.info("\nSem inconsistencias detectadas");
            taskLog.info("event=INCONSISTENCY_ADJUSTMENT_SKIP executionId={} reason=NONE", executionId);
        } else {

            for (UsuarioTV usuarioTV : naoLocalizadoWatch) {
                inconsistencias = inconsistencias.concat("\nDeletando usuario TV ID: " + usuarioTV.getId());
                taskLog.info("event=IXC_INCONSISTENCY_DELETE_START executionId={} usuarioTvId={} contratoId={}", executionId, usuarioTV.getId(), usuarioTV.getContratoId());

                try {
                    UsuarioTVDeleteResponse response = usuarioIXCService.deletar(usuarioTV);

                    if (response != null)
                        log.warn(response.getMessage());

                    log.info("Usuario TV {} deletado com sucesso", usuarioTV.getId());
                    taskLog.info("event=IXC_INCONSISTENCY_DELETE_SUCCESS executionId={} usuarioTvId={} contratoId={}", executionId, usuarioTV.getId(), usuarioTV.getContratoId());
                    ixcDel++;
                } catch (RuntimeException ex) {
                    log.error("Erro ao deletar usuario TV IXCSoft: {}", ex.getMessage());
                    taskLog.error("event=IXC_INCONSISTENCY_DELETE_ERROR executionId={} usuarioTvId={} contratoId={} error=\"{}\"", executionId, usuarioTV.getId(), usuarioTV.getContratoId(), sanitizeLogValue(ex.getMessage()));
                }

            };
            log.info("\n==Encerrando rotina==");

        }
        taskLog.info("event=EXECUTION_END executionId={} status=FINISHED", executionId);
    }

    private String sanitizeLogValue(String value) {
        if (value == null) {
            return "";
        }
        //IA: mantem eventos em uma linha para facilitar leitura e busca no arquivo operacional.
        return value.replace("\r", " ").replace("\n", " ").replace("\"", "'");
    }
}
