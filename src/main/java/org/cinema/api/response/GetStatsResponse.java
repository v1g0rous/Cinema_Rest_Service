package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetStatsResponse {
    @JsonProperty("current_income")
    int currentIncome;
    @JsonProperty("number_of_available_seats")
    int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    int purchasedTickets;

    public GetStatsResponse() {
    }

    public GetStatsResponse(int currentIncome, int availableSeats, int purchasedTickets) {
        this.currentIncome = currentIncome;
        this.availableSeats = availableSeats;
        this.purchasedTickets = purchasedTickets;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
