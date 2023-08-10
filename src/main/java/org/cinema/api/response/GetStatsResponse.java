package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetStatsResponse {
    @JsonProperty("current_income")
    int currentIncome;
    @JsonProperty("number_of_available_seats")
    int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    int purchasedTickets;
}
