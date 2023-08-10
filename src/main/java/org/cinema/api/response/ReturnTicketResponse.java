package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.cinema.entity.Seat;

public class ReturnTicketResponse {
    @JsonProperty("returned_ticket")
    Seat ticket;

    public ReturnTicketResponse(Seat ticket) {
        this.ticket = ticket;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
