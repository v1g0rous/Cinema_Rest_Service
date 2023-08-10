package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cinema.entity.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnTicketResponse {
    @JsonProperty("returned_ticket")
    Seat ticket;
}
