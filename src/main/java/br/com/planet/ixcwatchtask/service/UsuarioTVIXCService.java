package br.com.planet.ixcwatchtask.service;

import br.com.planet.ixcwatchtask.integration.ixc.GridParamConstructor;
import br.com.planet.ixcwatchtask.integration.ixc.UsuarioTVDeleteResponse;
import br.com.planet.ixcwatchtask.integration.ixc.UsuarioTVIntegration;
import br.com.planet.ixcwatchtask.integration.ixc.UsuarioTVResponse;
import br.com.planet.ixcwatchtask.model.ixc.UsuarioTV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioTVIXCService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioTVIXCService.class);

    private final UsuarioTVIntegration integration;

    public UsuarioTVIXCService(UsuarioTVIntegration integration) {
        this.integration = integration;
    }

    public List<UsuarioTV> listarAPI() {

        List<UsuarioTV> usuariosWatch = new ArrayList<>(this.parseResponse(integration.listar(montarGridParams(0L))));
        log.info("Usuarios TV Watch retornados: {}", usuariosWatch.size());
        return usuariosWatch;
    }

    public List<UsuarioTV> listarAPI(Long contratoId){

        List<UsuarioTV> usuariosWatch = new ArrayList<>(this.parseResponse(integration.listar(montarGridParams(contratoId))));
        log.info("Usuarios TV Watch retornados: {}", usuariosWatch.size());
        return usuariosWatch;
    }

    public UsuarioTVDeleteResponse deletar (UsuarioTV usuario){
        return integration.deletar(usuario.getId());
    }

    protected List<UsuarioTV> parseResponse(UsuarioTVResponse response) {
        try {

            List<UsuarioTV> usuarios = new ArrayList<>();

            //IA: protege a conversao contra respostas vazias ou incompletas da API IXC.
            if (response == null || "0".equals(response.getTotal()) || response.getRegistros() == null) {
                return usuarios;
            }

            response.getRegistros().forEach(u -> {

                String token = u.getTokenAssinanteWatch();

                if (token == null || token.isEmpty())
                    log.warn("Usuario TV Watch id {} contrato id {} nao possui token Watch", u.getId(), u.getIdContrato());


                UsuarioTV usuario = new UsuarioTV();
                usuario.setTicketWatch(token);
                usuario.setId(Long.parseLong(u.getId()));
                usuario.setContratoId(Long.parseLong(u.getIdContrato()));
                usuario.setLogin(u.getLogin());

                //IA: evita quebra quando o IXC retorna status_assinante_watch nulo.
                if ("0".equals(u.getStatusAssinanteWatch()))
                    usuario.setStatusTicket("Inativo");
                else
                    usuario.setStatusTicket("Ativo");

                usuarios.add(usuario);
            });
        return usuarios;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter contratos: ", e);
        }

    }

    private static String montarGridParams(Long contratoId) {
        List<String> params = new ArrayList<>();

        params.add(GridParamConstructor.SimpleGridParamConstructor(
                "tv_usuarios.plataforma",
                GridParamConstructor.EQUAL,
                "watch"
        ));

        if (contratoId != 0) {
            params.add(GridParamConstructor.SimpleGridParamConstructor(
                    "tv_usuarios.id_contrato",
                    GridParamConstructor.EQUAL,
                    Long.toString(contratoId)
            ));
        }

        return String.join(",", params);

    }


}
