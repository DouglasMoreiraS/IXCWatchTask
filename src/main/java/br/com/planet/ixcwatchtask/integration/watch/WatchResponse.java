package br.com.planet.ixcwatchtask.integration.watch;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchResponse {

    @JsonProperty("HasError")
    private Boolean hasError;

    @JsonProperty("ErrorMessage")
    private String errorMessage;

    @JsonProperty("ErrorNumber")
    private Integer errorNumber;

    @JsonProperty("IsValidationError")
    private Boolean isValidationError;

    @JsonProperty("Result")
    private Result result;

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    public Boolean getIsValidationError() {
        return isValidationError;
    }

    public void setIsValidationError(Boolean isValidationError) {
        this.isValidationError = isValidationError;
    }

    public Result  getResult() {
        return result;
    }

    public void setResult(Result  result) {
        this.result = result;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        @JsonProperty("list")
        private List<Item> list;

        public List<Item> getList() {
            return list;
        }

        public void setList(List<Item> list) {
            this.list = list;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {

        @JsonProperty("Pacote")
        private Integer pacote;

        @JsonProperty("DataCriacao")
        private String dataCriacao;

        @JsonProperty("EmailEnviado")
        private Boolean emailEnviado;

        @JsonProperty("EmailUsuario")
        private String emailUsuario;

        @JsonProperty("DataCriacaoUsuario")
        private String dataCriacaoUsuario;

        @JsonProperty("Ticket")
        private String ticket;

        @JsonProperty("Status")
        private Boolean status;

        @JsonProperty("IDIntegracaoAssinante")
        private String idIntegracaoAssinante;

        @JsonProperty("telefone")
        private String telefone;

        public Integer getPacote() {
            return pacote;
        }

        public void setPacote(Integer pacote) {
            this.pacote = pacote;
        }

        public String getDataCriacao() {
            return dataCriacao;
        }

        public void setDataCriacao(String dataCriacao) {
            this.dataCriacao = dataCriacao;
        }

        public Boolean getEmailEnviado() {
            return emailEnviado;
        }

        public void setEmailEnviado(Boolean emailEnviado) {
            this.emailEnviado = emailEnviado;
        }

        public String getEmailUsuario() {
            return emailUsuario;
        }

        public void setEmailUsuario(String emailUsuario) {
            this.emailUsuario = emailUsuario;
        }

        public String getDataCriacaoUsuario() {
            return dataCriacaoUsuario;
        }

        public void setDataCriacaoUsuario(String dataCriacaoUsuario) {
            this.dataCriacaoUsuario = dataCriacaoUsuario;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getIdIntegracaoAssinante() {
            return idIntegracaoAssinante;
        }

        public void setIdIntegracaoAssinante(String idIntegracaoAssinante) {
            this.idIntegracaoAssinante = idIntegracaoAssinante;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "pacote=" + pacote +
                    ", dataCriacao='" + dataCriacao + '\'' +
                    ", emailEnviado=" + emailEnviado +
                    ", emailUsuario='" + emailUsuario + '\'' +
                    ", dataCriacaoUsuario='" + dataCriacaoUsuario + '\'' +
                    ", ticket='" + ticket + '\'' +
                    ", status=" + status +
                    ", idIntegracaoAssinante='" + idIntegracaoAssinante + '\'' +
                    ", telefone='" + telefone + '\'' +
                    '}';
        }
    }
}
