package br.com.planet.ixcwatchtask.model;

public class ConsultaResponse {

    private boolean sucess;
    private String contratoId;
    private String ticketId;
    private String pacote;
    private String erro;

    public ConsultaResponse(boolean sucess, String contratoId, String ticketId, String pacote, String erro) {
        this.sucess = sucess;
        this.contratoId = contratoId;
        this.ticketId = ticketId;
        this.pacote = pacote;
        this.erro = erro;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getContratoId() {
        return contratoId;
    }

    public void setContratoId(String contratoId) {
        this.contratoId = contratoId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPacote() {
        return pacote;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    @Override
    public String toString() {
        return "ConsultaResponse{" +
                "sucess=" + sucess +
                ", contratoId='" + contratoId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", pacote='" + pacote + '\'' +
                ", erro='" + erro + '\'' +
                '}';
    }
}
