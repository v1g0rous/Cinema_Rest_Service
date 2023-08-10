package org.cinema.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.cinema.entity.Seat;

import java.util.List;

@JsonPropertyOrder({
        "total_rows",
        "total_columns",
        "available_seats"
})
public class GetSeatsResponse {
    @JsonProperty("available_seats")
    List<Seat> seats;
    @JsonProperty("total_rows")
    int totalRows;
    @JsonProperty("total_columns")
    int totalColumns;

    public GetSeatsResponse(int totalRows, int totalColumns, List<Seat> seats) {
        this.seats = seats;
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
    }

    public GetSeatsResponse() {
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }
}
