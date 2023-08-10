package org.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@JsonPropertyOrder({
        "row",
        "column",
        "price"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    int row;
    int column;
    int price;

    @JsonIgnore
    String token;
    @JsonIgnore
    Seat.STATUS status;

    public enum STATUS {
        AVAILABLE,
        PURCHASED
    }

    @JsonIgnore
    public boolean isAvailable(){
        return this.status == STATUS.AVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
