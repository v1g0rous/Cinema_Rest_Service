package org.cinema.entity;

import java.util.List;


public class CinemaTheater {
    List<Seat> seats;
    int totalRows;
    int totalColumns;

    public CinemaTheater(int totalRows, int totalColumns, List<Seat> seats) {
        this.seats = seats;
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
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
