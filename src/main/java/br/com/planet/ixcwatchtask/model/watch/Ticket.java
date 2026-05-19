package br.com.planet.ixcwatchtask.model.watch;

public class Ticket {

    public final static String PACOTE_UP = "3939";
    public final static String PACOTE_HBO = "3940";
    public final static String PACOTE_PREMIERE = "3944";
    public final static String PACOTE_HUB_PREMIUM = "15816";

    private String ticket;
    private String pacote;
    private String status;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getPacote() {
        return pacote;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket='" + ticket + '\'' +
                ", pacote='" + pacote + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
