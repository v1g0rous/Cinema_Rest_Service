package org.cinema.api.request;

public class ReturnTicketRequest {
    private String token;

    public ReturnTicketRequest(String token) {
        this.token = token;
    }

    public ReturnTicketRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
