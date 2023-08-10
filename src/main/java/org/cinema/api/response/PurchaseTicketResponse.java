package org.cinema.api.response;


import org.cinema.entity.Seat;

public class PurchaseTicketResponse {
    String token;
    Seat ticket;

    public PurchaseTicketResponse(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
