package org.cinema.service;

import org.cinema.entity.Seat;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public static final int FIRST_ROWS_PRICE = 10;
    public static final int SECOND_ROWS_PRICE = 8;
    public static final int MIDDLE_ROW = 4;

    public int getTicketPriceBySeat(Seat seat) {
        if (seat.getRow() <= MIDDLE_ROW) {
            return FIRST_ROWS_PRICE;
        } else {
            return SECOND_ROWS_PRICE;
        }
    }

    public int getTicketPriceBySeatRow(int row) {
        if (row <= MIDDLE_ROW) {
            return FIRST_ROWS_PRICE;
        } else {
            return SECOND_ROWS_PRICE;
        }
    }

    public void setTicketPriceBySeat(Seat seat) {
        seat.setPrice(getTicketPriceBySeat(seat));
    }
}
