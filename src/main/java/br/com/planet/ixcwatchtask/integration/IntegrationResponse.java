package br.com.planet.ixcwatchtask.integration;

public class IntegrationResponse {

    private boolean sucess;
    private String message;


    public IntegrationResponse(boolean sucess, String message) {
        this.sucess = sucess;
        this.message = message;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
