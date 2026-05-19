package br.com.planet.ixcwatchtask.service;

import br.com.planet.ixcwatchtask.integration.ixc.ContratoIntegration;
import br.com.planet.ixcwatchtask.integration.ixc.IXCIntegration;
import br.com.planet.ixcwatchtask.integration.ixc.ContratoResponse;
import br.com.planet.ixcwatchtask.integration.ixc.GridParamConstructor;
import br.com.planet.ixcwatchtask.model.ixc.Contrato;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoIXCService {

    private final ContratoIntegration integration;


    public ContratoIXCService(ContratoIntegration integration) {
        this.integration = integration;
    }

    public List<Contrato> listarAPI() {

       // List<Contrato> financeiroAtraso = new ArrayList<>(this.parseResponse(integration.listar(montarGridParams(null, "FA"))));
        List<Contrato> bloqueadosAuto = new ArrayList<>(this.parseResponse(integration.listar(montarGridParams(null, "CA"))));
        List<Contrato> bloqueadosManual = new ArrayList<>(this.parseResponse(integration.listar(montarGridParams(null, "CM"))));

        List<Contrato> todos = new ArrayList<>(bloqueadosAuto);
        todos.addAll(bloqueadosManual);
      //  todos.addAll(financeiroAtraso);

        return todos;
    }

    protected List<Contrato> parseResponse(ContratoResponse response) {
        try {
            List<Contrato> contratos = new ArrayList<>();

            //IA: protege a conversao contra respostas vazias ou incompletas da API IXC.
            if (response == null || "0".equals(response.getTotal()) || response.getRegistros() == null) {
                return contratos;
            }

            response.getRegistros().forEach(c -> {
                Contrato cc = new Contrato();
                cc.setId(Long.parseLong(c.getId()));

                try {
                    cc.setDataAtivacao(LocalDate.parse(c.getDataAtivacao()));
                } catch (DateTimeParseException e) {
                }

                cc.setPlano(c.getContrato());
                cc.setStatusAcesso(c.getStatusInternet());
                cc.setStatusContrato(c.getStatus());

                try {
                    cc.setDataCancelamento(LocalDate.parse(c.getDataCancelamento()));
                } catch (DateTimeParseException e) {
                }

                contratos.add(cc);
            });
            return contratos;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter contratos: ", e);
        }
    }

    private static String montarGridParams(String statusContrato, String statusAcesso) {
        List<String> params = new ArrayList<>();


      /*  if (statusContrato != null && !statusContrato.isBlank()) {
            params.add(GridParamConstructor.SimpleGridParamConstructor(
                    "cliente_contrato.status",
                    GridParamConstructor.EQUAL,
                    statusContrato
            ));
        }*/

        if (statusAcesso != null && !statusAcesso.isBlank()) {
            params.add(GridParamConstructor.SimpleGridParamConstructor(
                    "cliente_contrato.status_internet",
                    GridParamConstructor.EQUAL,
                    statusAcesso
            ));
        }

        return String.join(",", params);

    }

}
