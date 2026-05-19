package br.com.planet.ixcwatchtask.model.ixc;

import java.time.LocalDate;

public class Contrato {


    private Long id;
    private String statusContrato;
    private String statusAcesso;
    private LocalDate dataAtivacao;
    private String plano;

    private LocalDate dataCancelamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(String statusContrato) {
        this.statusContrato = statusContrato;
    }

    public String getStatusAcesso() {
        return statusAcesso;
    }

    public void setStatusAcesso(String statusAcesso) {
        this.statusAcesso = statusAcesso;
    }

    public LocalDate getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(LocalDate dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

}
