package org.cinema.api.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cinema.entity.Seat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseTicketResponse {
    String token;
    Seat ticket;
}
