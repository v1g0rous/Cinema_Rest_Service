package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cinema.entity.Seat;

import java.util.List;

@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeatsResponse {
    @JsonProperty("available_seats")
    List<Seat> seats;
    @JsonProperty("total_rows")
    int totalRows;
    @JsonProperty("total_columns")
    int totalColumns;
}
