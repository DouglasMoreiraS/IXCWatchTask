package br.com.planet.ixcwatchtask.integration.ixc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ContratoResponse {

    @JsonProperty("page")
    private String page;

    @JsonProperty("total")
    private String total;

    @JsonProperty("registros")
    private List<Registro> registros;

    // Getters e setters
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Registro {

        @JsonProperty("id_instalador")
        private String idInstalador;

        @JsonProperty("indicacao_contrato_id")
        private String indicacaoContratoId;

        @JsonProperty("id_indexador_reajuste")
        private String idIndexadorReajuste;

        @JsonProperty("data_desistencia")
        private String dataDesistencia;

        @JsonProperty("motivo_desistencia")
        private String motivoDesistencia;

        @JsonProperty("obs_desistencia")
        private String obsDesistencia;

        @JsonProperty("obs_contrato")
        private String obsContrato;

        @JsonProperty("alerta_contrato")
        private String alertaContrato;

        @JsonProperty("ids_contratos_recorrencia")
        private String idsContratosRecorrencia;

        @JsonProperty("id_responsavel_desistencia")
        private String idResponsavelDesistencia;

        @JsonProperty("id_responsavel_cancelamento")
        private String idResponsavelCancelamento;

        @JsonProperty("id_responsavel_negativacao")
        private String idResponsavelNegativacao;

        @JsonProperty("origem_cancelamento")
        private String origemCancelamento;

        @JsonProperty("situacao_financeira_contrato")
        private String situacaoFinanceiraContrato;

        @JsonProperty("id")
        private String id;

        @JsonProperty("id_filial")
        private String idFilial;

        @JsonProperty("status")
        private String status;

        @JsonProperty("status_internet")
        private String statusInternet;

        @JsonProperty("id_cliente")
        private String idCliente;

        @JsonProperty("data_assinatura")
        private String dataAssinatura;

        @JsonProperty("data_ativacao")
        private String dataAtivacao;

        @JsonProperty("data")
        private String data;

        @JsonProperty("data_renovacao")
        private String dataRenovacao;

        @JsonProperty("bloqueio_automatico")
        private String bloqueioAutomatico;

        @JsonProperty("imp_carteira")
        private String impCarteira;

        @JsonProperty("imp_importacao")
        private String impImportacao;

        @JsonProperty("data_expiracao")
        private String dataExpiracao;

        @JsonProperty("isentar_contrato")
        private String isentarContrato;

        @JsonProperty("pago_ate_data")
        private String pagoAteData;

        @JsonProperty("id_vd_contrato")
        private String idVdContrato;

        @JsonProperty("contrato")
        private String contrato;

        @JsonProperty("endereco")
        private String endereco;

        @JsonProperty("numero")
        private String numero;

        @JsonProperty("comissao")
        private String comissao;

        @JsonProperty("bairro")
        private String bairro;

        @JsonProperty("tipo")
        private String tipo;

        @JsonProperty("descricao_aux_plano_venda")
        private String descricaoAuxPlanoVenda;

        @JsonProperty("aviso_atraso")
        private String avisoAtraso;

        @JsonProperty("id_tipo_contrato")
        private String idTipoContrato;

        @JsonProperty("id_carteira_cobranca")
        private String idCarteiraCobranca;

        @JsonProperty("obs")
        private String obs;

        @JsonProperty("id_modelo")
        private String idModelo;

        @JsonProperty("status_velocidade")
        private String statusVelocidade;

        @JsonProperty("id_vendedor")
        private String idVendedor;

        @JsonProperty("cc_previsao")
        private String ccPrevisao;

        @JsonProperty("nao_avisar_ate")
        private String naoAvisarAte;

        @JsonProperty("nao_bloquear_ate")
        private String naoBloquearAte;

        @JsonProperty("id_tipo_documento")
        private String idTipoDocumento;

        @JsonProperty("tipo_doc_opc")
        private String tipoDocOpc;

        @JsonProperty("desconto_fidelidade")
        private String descontoFidelidade;

        @JsonProperty("tipo_doc_opc2")
        private String tipoDocOpc2;

        @JsonProperty("tipo_doc_opc3")
        private String tipoDocOpc3;

        @JsonProperty("taxa_improdutiva")
        private String taxaImprodutiva;

        @JsonProperty("tipo_doc_opc4")
        private String tipoDocOpc4;

        @JsonProperty("desbloqueio_confianca")
        private String desbloqueioConfianca;

        @JsonProperty("data_negativacao")
        private String dataNegativacao;

        @JsonProperty("data_acesso_desativado")
        private String dataAcessoDesativado;

        @JsonProperty("motivo_cancelamento")
        private String motivoCancelamento;

        @JsonProperty("data_cancelamento")
        private String dataCancelamento;

        @JsonProperty("obs_cancelamento")
        private String obsCancelamento;

        @JsonProperty("id_vendedor_ativ")
        private String idVendedorAtiv;

        @JsonProperty("fidelidade")
        private String fidelidade;

        @JsonProperty("tipo_cobranca")
        private String tipoCobranca;

        @JsonProperty("desbloqueio_confianca_ativo")
        private String desbloqueioConfiancaAtivo;

        @JsonProperty("id_responsavel")
        private String idResponsavel;

        @JsonProperty("taxa_instalacao")
        private String taxaInstalacao;

        @JsonProperty("protocolo_negativacao")
        private String protocoloNegativacao;

        @JsonProperty("dt_ult_bloq_auto")
        private String dtUltBloqAuto;

        @JsonProperty("dt_ult_bloq_manual")
        private String dtUltBloqManual;

        @JsonProperty("dt_ult_des_bloq_conf")
        private String dtUltDesBloqConf;

        @JsonProperty("dt_ult_ativacao")
        private String dtUltAtivacao;

        @JsonProperty("avalista_1")
        private String avalista1;

        @JsonProperty("dt_ult_finan_atraso")
        private String dtUltFinanAtraso;

        @JsonProperty("avalista_2")
        private String avalista2;

        @JsonProperty("dt_utl_negativacao")
        private String dtUtlNegativacao;

        @JsonProperty("data_cadastro_sistema")
        private String dataCadastroSistema;

        @JsonProperty("ultima_atualizacao")
        private String ultimaAtualizacao;

        @JsonProperty("complemento")
        private String complemento;

        @JsonProperty("cep")
        private String cep;

        @JsonProperty("cidade")
        private String cidade;

        @JsonProperty("renovacao_automatica")
        private String renovacaoAutomatica;

        @JsonProperty("motivo_inclusao")
        private String motivoInclusao;

        @JsonProperty("liberacao_bloqueio_manual")
        private String liberacaoBloqueioManual;

        @JsonProperty("contrato_suspenso")
        private String contratoSuspenso;

        @JsonProperty("imp_inicial")
        private String impInicial;

        @JsonProperty("imp_final")
        private String impFinal;

        @JsonProperty("ativacao_valor_parcela")
        private String ativacaoValorParcela;

        @JsonProperty("id_tipo_doc_ativ")
        private String idTipoDocAtiv;

        @JsonProperty("id_produto_ativ")
        private String idProdutoAtiv;

        @JsonProperty("id_cond_pag_ativ")
        private String idCondPagAtiv;

        @JsonProperty("endereco_padrao_cliente")
        private String enderecoPadraoCliente;

        @JsonProperty("referencia")
        private String referencia;

        @JsonProperty("id_condominio")
        private String idCondominio;

        @JsonProperty("nf_info_adicionais")
        private String nfInfoAdicionais;

        @JsonProperty("assinatura_digital")
        private String assinaturaDigital;

        @JsonProperty("tipo_produtos_plano")
        private String tipoProdutosPlano;

        @JsonProperty("bloco")
        private String bloco;

        @JsonProperty("apartamento")
        private String apartamento;

        @JsonProperty("latitude")
        private String latitude;

        @JsonProperty("longitude")
        private String longitude;

        @JsonProperty("num_parcelas_atraso")
        private String numParcelasAtraso;

        @JsonProperty("dt_ult_desiste")
        private String dtUltDesiste;

        @JsonProperty("id_contrato_principal")
        private String idContratoPrincipal;

        @JsonProperty("gerar_finan_assin_digital_contrato")
        private String gerarFinanAssinDigitalContrato;

        @JsonProperty("credit_card_recorrente_token")
        private String creditCardRecorrenteToken;

        @JsonProperty("credit_card_recorrente_bandeira_cartao")
        private String creditCardRecorrenteBandeiraCartao;

        @JsonProperty("id_motivo_negativacao")
        private String idMotivoNegativacao;

        @JsonProperty("obs_negativacao")
        private String obsNegativacao;

        @JsonProperty("restricao_auto_desbloqueio")
        private String restricaoAutoDesbloqueio;

        @JsonProperty("motivo_restricao_auto_desbloq")
        private String motivoRestricaoAutoDesbloq;

        @JsonProperty("nao_susp_parc_ate")
        private String naoSuspParcAte;

        @JsonProperty("liberacao_suspensao_parcial")
        private String liberacaoSuspensaoParcial;

        @JsonProperty("utilizando_auto_libera_susp_parc")
        private String utilizandoAutoLiberaSuspParc;

        @JsonProperty("restricao_auto_libera_susp_parcial")
        private String restricaoAutoLiberaSuspParcial;

        @JsonProperty("motivo_restri_auto_libera_parc")
        private String motivoRestricaoAutoLiberaParc;

        @JsonProperty("data_inicial_suspensao")
        private String dataInicialSuspensao;

        @JsonProperty("data_final_suspensao")
        private String dataFinalSuspensao;

        @JsonProperty("data_retomada_contrato")
        private String dataRetomadaContrato;

        @JsonProperty("dt_ult_liberacao_susp_parc")
        private String dtUltLiberacaoSuspParc;

        @JsonProperty("base_geracao_tipo_doc")
        private String baseGeracaoTipoDoc;

        @JsonProperty("integracao_assinatura_digital")
        private String integracaoAssinaturaDigital;

        @JsonProperty("url_assinatura_digital")
        private String urlAssinaturaDigital;

        @JsonProperty("token_assinatura_digital")
        private String tokenAssinaturaDigital;

        @JsonProperty("testemunha_assinatura_digital")
        private String testemunhaAssinaturaDigital;

        @JsonProperty("document_photo")
        private String documentPhoto;

        @JsonProperty("selfie_photo")
        private String selfiePhoto;

        @JsonProperty("tipo_localidade")
        private String tipoLocalidade;

        @JsonProperty("estrato_social_col")
        private String estratoSocialCol;

        @JsonProperty("agrupar_financeiro_contrato")
        private String agruparFinanceiroContrato;

        public String getIdInstalador() {
            return idInstalador;
        }

        public void setIdInstalador(String idInstalador) {
            this.idInstalador = idInstalador;
        }

        public String getIndicacaoContratoId() {
            return indicacaoContratoId;
        }

        public void setIndicacaoContratoId(String indicacaoContratoId) {
            this.indicacaoContratoId = indicacaoContratoId;
        }

        public String getIdIndexadorReajuste() {
            return idIndexadorReajuste;
        }

        public void setIdIndexadorReajuste(String idIndexadorReajuste) {
            this.idIndexadorReajuste = idIndexadorReajuste;
        }

        public String getDataDesistencia() {
            return dataDesistencia;
        }

        public void setDataDesistencia(String dataDesistencia) {
            this.dataDesistencia = dataDesistencia;
        }

        public String getMotivoDesistencia() {
            return motivoDesistencia;
        }

        public void setMotivoDesistencia(String motivoDesistencia) {
            this.motivoDesistencia = motivoDesistencia;
        }

        public String getObsDesistencia() {
            return obsDesistencia;
        }

        public void setObsDesistencia(String obsDesistencia) {
            this.obsDesistencia = obsDesistencia;
        }

        public String getObsContrato() {
            return obsContrato;
        }

        public void setObsContrato(String obsContrato) {
            this.obsContrato = obsContrato;
        }

        public String getAlertaContrato() {
            return alertaContrato;
        }

        public void setAlertaContrato(String alertaContrato) {
            this.alertaContrato = alertaContrato;
        }

        public String getIdsContratosRecorrencia() {
            return idsContratosRecorrencia;
        }

        public void setIdsContratosRecorrencia(String idsContratosRecorrencia) {
            this.idsContratosRecorrencia = idsContratosRecorrencia;
        }

        public String getIdResponsavelDesistencia() {
            return idResponsavelDesistencia;
        }

        public void setIdResponsavelDesistencia(String idResponsavelDesistencia) {
            this.idResponsavelDesistencia = idResponsavelDesistencia;
        }

        public String getIdResponsavelCancelamento() {
            return idResponsavelCancelamento;
        }

        public void setIdResponsavelCancelamento(String idResponsavelCancelamento) {
            this.idResponsavelCancelamento = idResponsavelCancelamento;
        }

        public String getIdResponsavelNegativacao() {
            return idResponsavelNegativacao;
        }

        public void setIdResponsavelNegativacao(String idResponsavelNegativacao) {
            this.idResponsavelNegativacao = idResponsavelNegativacao;
        }

        public String getOrigemCancelamento() {
            return origemCancelamento;
        }

        public void setOrigemCancelamento(String origemCancelamento) {
            this.origemCancelamento = origemCancelamento;
        }

        public String getSituacaoFinanceiraContrato() {
            return situacaoFinanceiraContrato;
        }

        public void setSituacaoFinanceiraContrato(String situacaoFinanceiraContrato) {
            this.situacaoFinanceiraContrato = situacaoFinanceiraContrato;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdFilial() {
            return idFilial;
        }

        public void setIdFilial(String idFilial) {
            this.idFilial = idFilial;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusInternet() {
            return statusInternet;
        }

        public void setStatusInternet(String statusInternet) {
            this.statusInternet = statusInternet;
        }

        public String getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(String idCliente) {
            this.idCliente = idCliente;
        }

        public String getDataAssinatura() {
            return dataAssinatura;
        }

        public void setDataAssinatura(String dataAssinatura) {
            this.dataAssinatura = dataAssinatura;
        }

        public String getDataAtivacao() {
            return dataAtivacao;
        }

        public void setDataAtivacao(String dataAtivacao) {
            this.dataAtivacao = dataAtivacao;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getDataRenovacao() {
            return dataRenovacao;
        }

        public void setDataRenovacao(String dataRenovacao) {
            this.dataRenovacao = dataRenovacao;
        }

        public String getBloqueioAutomatico() {
            return bloqueioAutomatico;
        }

        public void setBloqueioAutomatico(String bloqueioAutomatico) {
            this.bloqueioAutomatico = bloqueioAutomatico;
        }

        public String getImpCarteira() {
            return impCarteira;
        }

        public void setImpCarteira(String impCarteira) {
            this.impCarteira = impCarteira;
        }

        public String getDataExpiracao() {
            return dataExpiracao;
        }

        public void setDataExpiracao(String dataExpiracao) {
            this.dataExpiracao = dataExpiracao;
        }

        public String getIsentarContrato() {
            return isentarContrato;
        }

        public void setIsentarContrato(String isentarContrato) {
            this.isentarContrato = isentarContrato;
        }

        public String getPagoAteData() {
            return pagoAteData;
        }

        public void setPagoAteData(String pagoAteData) {
            this.pagoAteData = pagoAteData;
        }

        public String getIdVdContrato() {
            return idVdContrato;
        }

        public void setIdVdContrato(String idVdContrato) {
            this.idVdContrato = idVdContrato;
        }

        public String getContrato() {
            return contrato;
        }

        public void setContrato(String contrato) {
            this.contrato = contrato;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getComissao() {
            return comissao;
        }

        public void setComissao(String comissao) {
            this.comissao = comissao;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getDescricaoAuxPlanoVenda() {
            return descricaoAuxPlanoVenda;
        }

        public void setDescricaoAuxPlanoVenda(String descricaoAuxPlanoVenda) {
            this.descricaoAuxPlanoVenda = descricaoAuxPlanoVenda;
        }

        public String getAvisoAtraso() {
            return avisoAtraso;
        }

        public void setAvisoAtraso(String avisoAtraso) {
            this.avisoAtraso = avisoAtraso;
        }

        public String getIdTipoContrato() {
            return idTipoContrato;
        }

        public void setIdTipoContrato(String idTipoContrato) {
            this.idTipoContrato = idTipoContrato;
        }

        public String getIdCarteiraCobranca() {
            return idCarteiraCobranca;
        }

        public void setIdCarteiraCobranca(String idCarteiraCobranca) {
            this.idCarteiraCobranca = idCarteiraCobranca;
        }

        public String getObs() {
            return obs;
        }

        public void setObs(String obs) {
            this.obs = obs;
        }

        public String getIdModelo() {
            return idModelo;
        }

        public void setIdModelo(String idModelo) {
            this.idModelo = idModelo;
        }

        public String getStatusVelocidade() {
            return statusVelocidade;
        }

        public void setStatusVelocidade(String statusVelocidade) {
            this.statusVelocidade = statusVelocidade;
        }

        public String getIdVendedor() {
            return idVendedor;
        }

        public void setIdVendedor(String idVendedor) {
            this.idVendedor = idVendedor;
        }

        public String getCcPrevisao() {
            return ccPrevisao;
        }

        public void setCcPrevisao(String ccPrevisao) {
            this.ccPrevisao = ccPrevisao;
        }

        public String getNaoAvisarAte() {
            return naoAvisarAte;
        }

        public void setNaoAvisarAte(String naoAvisarAte) {
            this.naoAvisarAte = naoAvisarAte;
        }

        public String getNaoBloquearAte() {
            return naoBloquearAte;
        }

        public void setNaoBloquearAte(String naoBloquearAte) {
            this.naoBloquearAte = naoBloquearAte;
        }

        public String getIdTipoDocumento() {
            return idTipoDocumento;
        }

        public void setIdTipoDocumento(String idTipoDocumento) {
            this.idTipoDocumento = idTipoDocumento;
        }

        public String getTipoDocOpc() {
            return tipoDocOpc;
        }

        public void setTipoDocOpc(String tipoDocOpc) {
            this.tipoDocOpc = tipoDocOpc;
        }

        public String getDescontoFidelidade() {
            return descontoFidelidade;
        }

        public void setDescontoFidelidade(String descontoFidelidade) {
            this.descontoFidelidade = descontoFidelidade;
        }

        public String getTipoDocOpc2() {
            return tipoDocOpc2;
        }

        public void setTipoDocOpc2(String tipoDocOpc2) {
            this.tipoDocOpc2 = tipoDocOpc2;
        }

        public String getTipoDocOpc3() {
            return tipoDocOpc3;
        }

        public void setTipoDocOpc3(String tipoDocOpc3) {
            this.tipoDocOpc3 = tipoDocOpc3;
        }

        public String getTaxaImprodutiva() {
            return taxaImprodutiva;
        }

        public void setTaxaImprodutiva(String taxaImprodutiva) {
            this.taxaImprodutiva = taxaImprodutiva;
        }

        public String getTipoDocOpc4() {
            return tipoDocOpc4;
        }

        public void setTipoDocOpc4(String tipoDocOpc4) {
            this.tipoDocOpc4 = tipoDocOpc4;
        }

        public String getDesbloqueioConfianca() {
            return desbloqueioConfianca;
        }

        public void setDesbloqueioConfianca(String desbloqueioConfianca) {
            this.desbloqueioConfianca = desbloqueioConfianca;
        }

        public String getDataNegativacao() {
            return dataNegativacao;
        }

        public void setDataNegativacao(String dataNegativacao) {
            this.dataNegativacao = dataNegativacao;
        }

        public String getDataAcessoDesativado() {
            return dataAcessoDesativado;
        }

        public void setDataAcessoDesativado(String dataAcessoDesativado) {
            this.dataAcessoDesativado = dataAcessoDesativado;
        }

        public String getMotivoCancelamento() {
            return motivoCancelamento;
        }

        public void setMotivoCancelamento(String motivoCancelamento) {
            this.motivoCancelamento = motivoCancelamento;
        }

        public String getDataCancelamento() {
            return dataCancelamento;
        }

        public void setDataCancelamento(String dataCancelamento) {
            this.dataCancelamento = dataCancelamento;
        }

        public String getObsCancelamento() {
            return obsCancelamento;
        }

        public void setObsCancelamento(String obsCancelamento) {
            this.obsCancelamento = obsCancelamento;
        }

        public String getIdVendedorAtiv() {
            return idVendedorAtiv;
        }

        public void setIdVendedorAtiv(String idVendedorAtiv) {
            this.idVendedorAtiv = idVendedorAtiv;
        }

        public String getFidelidade() {
            return fidelidade;
        }

        public void setFidelidade(String fidelidade) {
            this.fidelidade = fidelidade;
        }

        public String getTipoCobranca() {
            return tipoCobranca;
        }

        public void setTipoCobranca(String tipoCobranca) {
            this.tipoCobranca = tipoCobranca;
        }

        public String getDesbloqueioConfiancaAtivo() {
            return desbloqueioConfiancaAtivo;
        }

        public void setDesbloqueioConfiancaAtivo(String desbloqueioConfiancaAtivo) {
            this.desbloqueioConfiancaAtivo = desbloqueioConfiancaAtivo;
        }

        public String getIdResponsavel() {
            return idResponsavel;
        }

        public void setIdResponsavel(String idResponsavel) {
            this.idResponsavel = idResponsavel;
        }

        public String getTaxaInstalacao() {
            return taxaInstalacao;
        }

        public void setTaxaInstalacao(String taxaInstalacao) {
            this.taxaInstalacao = taxaInstalacao;
        }

        public String getProtocoloNegativacao() {
            return protocoloNegativacao;
        }

        public void setProtocoloNegativacao(String protocoloNegativacao) {
            this.protocoloNegativacao = protocoloNegativacao;
        }

        public String getDtUltBloqAuto() {
            return dtUltBloqAuto;
        }

        public void setDtUltBloqAuto(String dtUltBloqAuto) {
            this.dtUltBloqAuto = dtUltBloqAuto;
        }

        public String getDtUltBloqManual() {
            return dtUltBloqManual;
        }

        public void setDtUltBloqManual(String dtUltBloqManual) {
            this.dtUltBloqManual = dtUltBloqManual;
        }

        public String getDtUltDesBloqConf() {
            return dtUltDesBloqConf;
        }

        public void setDtUltDesBloqConf(String dtUltDesBloqConf) {
            this.dtUltDesBloqConf = dtUltDesBloqConf;
        }

        public String getDtUltAtivacao() {
            return dtUltAtivacao;
        }

        public void setDtUltAtivacao(String dtUltAtivacao) {
            this.dtUltAtivacao = dtUltAtivacao;
        }

        public String getAvalista1() {
            return avalista1;
        }

        public void setAvalista1(String avalista1) {
            this.avalista1 = avalista1;
        }

        public String getDtUltFinanAtraso() {
            return dtUltFinanAtraso;
        }

        public void setDtUltFinanAtraso(String dtUltFinanAtraso) {
            this.dtUltFinanAtraso = dtUltFinanAtraso;
        }

        public String getAvalista2() {
            return avalista2;
        }

        public void setAvalista2(String avalista2) {
            this.avalista2 = avalista2;
        }

        public String getDtUtlNegativacao() {
            return dtUtlNegativacao;
        }

        public void setDtUtlNegativacao(String dtUtlNegativacao) {
            this.dtUtlNegativacao = dtUtlNegativacao;
        }

        public String getDataCadastroSistema() {
            return dataCadastroSistema;
        }

        public void setDataCadastroSistema(String dataCadastroSistema) {
            this.dataCadastroSistema = dataCadastroSistema;
        }

        public String getUltimaAtualizacao() {
            return ultimaAtualizacao;
        }

        public void setUltimaAtualizacao(String ultimaAtualizacao) {
            this.ultimaAtualizacao = ultimaAtualizacao;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getRenovacaoAutomatica() {
            return renovacaoAutomatica;
        }

        public void setRenovacaoAutomatica(String renovacaoAutomatica) {
            this.renovacaoAutomatica = renovacaoAutomatica;
        }

        public String getMotivoInclusao() {
            return motivoInclusao;
        }

        public void setMotivoInclusao(String motivoInclusao) {
            this.motivoInclusao = motivoInclusao;
        }

        public String getLiberacaoBloqueioManual() {
            return liberacaoBloqueioManual;
        }

        public void setLiberacaoBloqueioManual(String liberacaoBloqueioManual) {
            this.liberacaoBloqueioManual = liberacaoBloqueioManual;
        }

        public String getContratoSuspenso() {
            return contratoSuspenso;
        }

        public void setContratoSuspenso(String contratoSuspenso) {
            this.contratoSuspenso = contratoSuspenso;
        }

        public String getImpInicial() {
            return impInicial;
        }

        public void setImpInicial(String impInicial) {
            this.impInicial = impInicial;
        }

        public String getImpFinal() {
            return impFinal;
        }

        public void setImpFinal(String impFinal) {
            this.impFinal = impFinal;
        }

        public String getAtivacaoValorParcela() {
            return ativacaoValorParcela;
        }

        public void setAtivacaoValorParcela(String ativacaoValorParcela) {
            this.ativacaoValorParcela = ativacaoValorParcela;
        }

        public String getIdTipoDocAtiv() {
            return idTipoDocAtiv;
        }

        public void setIdTipoDocAtiv(String idTipoDocAtiv) {
            this.idTipoDocAtiv = idTipoDocAtiv;
        }

        public String getIdProdutoAtiv() {
            return idProdutoAtiv;
        }

        public void setIdProdutoAtiv(String idProdutoAtiv) {
            this.idProdutoAtiv = idProdutoAtiv;
        }

        public String getIdCondPagAtiv() {
            return idCondPagAtiv;
        }

        public void setIdCondPagAtiv(String idCondPagAtiv) {
            this.idCondPagAtiv = idCondPagAtiv;
        }

        public String getEnderecoPadraoCliente() {
            return enderecoPadraoCliente;
        }

        public void setEnderecoPadraoCliente(String enderecoPadraoCliente) {
            this.enderecoPadraoCliente = enderecoPadraoCliente;
        }

        public String getReferencia() {
            return referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }

        public String getIdCondominio() {
            return idCondominio;
        }

        public void setIdCondominio(String idCondominio) {
            this.idCondominio = idCondominio;
        }

        public String getNfInfoAdicionais() {
            return nfInfoAdicionais;
        }

        public void setNfInfoAdicionais(String nfInfoAdicionais) {
            this.nfInfoAdicionais = nfInfoAdicionais;
        }

        public String getAssinaturaDigital() {
            return assinaturaDigital;
        }

        public void setAssinaturaDigital(String assinaturaDigital) {
            this.assinaturaDigital = assinaturaDigital;
        }

        public String getTipoProdutosPlano() {
            return tipoProdutosPlano;
        }

        public void setTipoProdutosPlano(String tipoProdutosPlano) {
            this.tipoProdutosPlano = tipoProdutosPlano;
        }

        public String getBloco() {
            return bloco;
        }

        public void setBloco(String bloco) {
            this.bloco = bloco;
        }

        public String getApartamento() {
            return apartamento;
        }

        public void setApartamento(String apartamento) {
            this.apartamento = apartamento;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getNumParcelasAtraso() {
            return numParcelasAtraso;
        }

        public void setNumParcelasAtraso(String numParcelasAtraso) {
            this.numParcelasAtraso = numParcelasAtraso;
        }

        public String getDtUltDesiste() {
            return dtUltDesiste;
        }

        public void setDtUltDesiste(String dtUltDesiste) {
            this.dtUltDesiste = dtUltDesiste;
        }

        public String getIdContratoPrincipal() {
            return idContratoPrincipal;
        }

        public void setIdContratoPrincipal(String idContratoPrincipal) {
            this.idContratoPrincipal = idContratoPrincipal;
        }

        public String getGerarFinanAssinDigitalContrato() {
            return gerarFinanAssinDigitalContrato;
        }

        public void setGerarFinanAssinDigitalContrato(String gerarFinanAssinDigitalContrato) {
            this.gerarFinanAssinDigitalContrato = gerarFinanAssinDigitalContrato;
        }

        public String getCreditCardRecorrenteToken() {
            return creditCardRecorrenteToken;
        }

        public void setCreditCardRecorrenteToken(String creditCardRecorrenteToken) {
            this.creditCardRecorrenteToken = creditCardRecorrenteToken;
        }

        public String getCreditCardRecorrenteBandeiraCartao() {
            return creditCardRecorrenteBandeiraCartao;
        }

        public void setCreditCardRecorrenteBandeiraCartao(String creditCardRecorrenteBandeiraCartao) {
            this.creditCardRecorrenteBandeiraCartao = creditCardRecorrenteBandeiraCartao;
        }

        public String getIdMotivoNegativacao() {
            return idMotivoNegativacao;
        }

        public void setIdMotivoNegativacao(String idMotivoNegativacao) {
            this.idMotivoNegativacao = idMotivoNegativacao;
        }

        public String getObsNegativacao() {
            return obsNegativacao;
        }

        public void setObsNegativacao(String obsNegativacao) {
            this.obsNegativacao = obsNegativacao;
        }

        public String getRestricaoAutoDesbloqueio() {
            return restricaoAutoDesbloqueio;
        }

        public void setRestricaoAutoDesbloqueio(String restricaoAutoDesbloqueio) {
            this.restricaoAutoDesbloqueio = restricaoAutoDesbloqueio;
        }

        public String getMotivoRestricaoAutoDesbloq() {
            return motivoRestricaoAutoDesbloq;
        }

        public void setMotivoRestricaoAutoDesbloq(String motivoRestricaoAutoDesbloq) {
            this.motivoRestricaoAutoDesbloq = motivoRestricaoAutoDesbloq;
        }

        public String getNaoSuspParcAte() {
            return naoSuspParcAte;
        }

        public void setNaoSuspParcAte(String naoSuspParcAte) {
            this.naoSuspParcAte = naoSuspParcAte;
        }

        public String getLiberacaoSuspensaoParcial() {
            return liberacaoSuspensaoParcial;
        }

        public void setLiberacaoSuspensaoParcial(String liberacaoSuspensaoParcial) {
            this.liberacaoSuspensaoParcial = liberacaoSuspensaoParcial;
        }

        public String getUtilizandoAutoLiberaSuspParc() {
            return utilizandoAutoLiberaSuspParc;
        }

        public void setUtilizandoAutoLiberaSuspParc(String utilizandoAutoLiberaSuspParc) {
            this.utilizandoAutoLiberaSuspParc = utilizandoAutoLiberaSuspParc;
        }

        public String getRestricaoAutoLiberaSuspParcial() {
            return restricaoAutoLiberaSuspParcial;
        }

        public void setRestricaoAutoLiberaSuspParcial(String restricaoAutoLiberaSuspParcial) {
            this.restricaoAutoLiberaSuspParcial = restricaoAutoLiberaSuspParcial;
        }

        public String getMotivoRestricaoAutoLiberaParc() {
            return motivoRestricaoAutoLiberaParc;
        }

        public void setMotivoRestricaoAutoLiberaParc(String motivoRestricaoAutoLiberaParc) {
            this.motivoRestricaoAutoLiberaParc = motivoRestricaoAutoLiberaParc;
        }

        public String getDataInicialSuspensao() {
            return dataInicialSuspensao;
        }

        public void setDataInicialSuspensao(String dataInicialSuspensao) {
            this.dataInicialSuspensao = dataInicialSuspensao;
        }

        public String getDataFinalSuspensao() {
            return dataFinalSuspensao;
        }

        public void setDataFinalSuspensao(String dataFinalSuspensao) {
            this.dataFinalSuspensao = dataFinalSuspensao;
        }

        public String getDataRetomadaContrato() {
            return dataRetomadaContrato;
        }

        public void setDataRetomadaContrato(String dataRetomadaContrato) {
            this.dataRetomadaContrato = dataRetomadaContrato;
        }

        public String getDtUltLiberacaoSuspParc() {
            return dtUltLiberacaoSuspParc;
        }

        public void setDtUltLiberacaoSuspParc(String dtUltLiberacaoSuspParc) {
            this.dtUltLiberacaoSuspParc = dtUltLiberacaoSuspParc;
        }

        public String getBaseGeracaoTipoDoc() {
            return baseGeracaoTipoDoc;
        }

        public void setBaseGeracaoTipoDoc(String baseGeracaoTipoDoc) {
            this.baseGeracaoTipoDoc = baseGeracaoTipoDoc;
        }

        public String getIntegracaoAssinaturaDigital() {
            return integracaoAssinaturaDigital;
        }

        public void setIntegracaoAssinaturaDigital(String integracaoAssinaturaDigital) {
            this.integracaoAssinaturaDigital = integracaoAssinaturaDigital;
        }

        public String getUrlAssinaturaDigital() {
            return urlAssinaturaDigital;
        }

        public void setUrlAssinaturaDigital(String urlAssinaturaDigital) {
            this.urlAssinaturaDigital = urlAssinaturaDigital;
        }

        public String getTokenAssinaturaDigital() {
            return tokenAssinaturaDigital;
        }

        public void setTokenAssinaturaDigital(String tokenAssinaturaDigital) {
            this.tokenAssinaturaDigital = tokenAssinaturaDigital;
        }

        public String getTestemunhaAssinaturaDigital() {
            return testemunhaAssinaturaDigital;
        }

        public void setTestemunhaAssinaturaDigital(String testemunhaAssinaturaDigital) {
            this.testemunhaAssinaturaDigital = testemunhaAssinaturaDigital;
        }

        public String getDocumentPhoto() {
            return documentPhoto;
        }

        public void setDocumentPhoto(String documentPhoto) {
            this.documentPhoto = documentPhoto;
        }

        public String getSelfiePhoto() {
            return selfiePhoto;
        }

        public void setSelfiePhoto(String selfiePhoto) {
            this.selfiePhoto = selfiePhoto;
        }

        public String getTipoLocalidade() {
            return tipoLocalidade;
        }

        public void setTipoLocalidade(String tipoLocalidade) {
            this.tipoLocalidade = tipoLocalidade;
        }

        public String getEstratoSocialCol() {
            return estratoSocialCol;
        }

        public void setEstratoSocialCol(String estratoSocialCol) {
            this.estratoSocialCol = estratoSocialCol;
        }

        public String getAgruparFinanceiroContrato() {
            return agruparFinanceiroContrato;
        }

        public void setAgruparFinanceiroContrato(String agruparFinanceiroContrato) {
            this.agruparFinanceiroContrato = agruparFinanceiroContrato;
        }

        public String getImpImportacao() {
            return impImportacao;
        }

        public void setImpImportacao(String impImportacao) {
            this.impImportacao = impImportacao;
        }

    }

}
