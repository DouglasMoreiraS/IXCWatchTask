package br.com.planet.ixcwatchtask.model.ixc;

public class UsuarioTV {

    private Long id;
    private Long contratoId;
    private String login;
    private String ticketWatch;
    private String statusTicket;
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContratoId() {
        return contratoId;
    }

    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTicketWatch() {
        return ticketWatch;
    }

    public void setTicketWatch(String ticketWatch) {
        this.ticketWatch = ticketWatch;
    }

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioTV{" +
                "id=" + id +
                ", contratoId=" + contratoId +
                ", login='" + login + '\'' +
                ", ticketWatch='" + ticketWatch + '\'' +
                ", statusTicket='" + statusTicket + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
