package org.cinema.service;


import jakarta.annotation.PostConstruct;
import org.cinema.entity.CinemaTheater;
import org.cinema.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {
    public static final int MIN_CINEMA_ROW = 1;
    public static final int MAX_CINEMA_ROW = 9;
    public static final int MIN_CINEMA_COLUMN = 1;
    public static final int MAX_CINEMA_COLUMN = 9;
    private String password = "super_secret";
    private CinemaTheater cinemaTheater;
    private PaymentService paymentService;

    public TicketService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public CinemaTheater getCinemaTheater() {
        return cinemaTheater;
    }


    public List<Seat> getAvailableSeats() {
        return cinemaTheater
                .getSeats()
                .stream()
                .filter(seat -> seat.getStatus() == Seat.STATUS.AVAILABLE)
                .collect(Collectors.toList());
    }

    @PostConstruct
    private CinemaTheater createCinemaTheater() {
        cinemaTheater = new CinemaTheater(initializeSeats(), 9, 9);
        return cinemaTheater;
    }

    private List<Seat> initializeSeats() {
        List<Seat> seats = new ArrayList<>();

        for (int i = MIN_CINEMA_ROW; i <= MAX_CINEMA_ROW; i++) {
            for (int j = MIN_CINEMA_COLUMN; j <= MAX_CINEMA_COLUMN; j++) {
                Seat seat = Seat.builder()
                        .row(i)
                        .column(j)
                        .price(paymentService.getTicketPriceBySeatRow(i))
                        .status(Seat.STATUS.AVAILABLE).build();

                seats.add(seat);
            }
        }
        return seats;
    }

    public boolean seatExists(Seat seat) {
        boolean isRowWithinRange = seat.getRow() >= MIN_CINEMA_ROW && seat.getRow() <= MAX_CINEMA_ROW;
        boolean isColumnWithinRange = seat.getColumn() >= MIN_CINEMA_COLUMN && seat.getColumn() <= MAX_CINEMA_COLUMN;

        return isRowWithinRange && isColumnWithinRange;
    }

    public boolean isSeatAvailable(Seat seat) {

        return cinemaTheater.getSeats().stream()
                .filter(s -> s.equals(seat)) // filter to only an 'equal' seat by row/column
                .findFirst() // get that seat container (Optional that may contain seat or not)
                .map(Seat::isAvailable) // if it contains - call isAvailable on that seat
                .orElse(false); // else return false
    }

    public Seat bookSeat(Seat seat) {

        Seat seatDB = findSeatByRowColumn(seat);

        if (seatDB == null) {
            return null;
        }

        paymentService.setTicketPriceBySeat(seatDB);
        seatDB.setToken(generateUniqueToken());
        seatDB.setStatus(Seat.STATUS.PURCHASED);

        return seatDB;
    }

    private Seat findSeatByRowColumn(Seat seat) {
        Seat foundSeat = cinemaTheater.getSeats().stream()
                .filter(s -> s.equals(seat)) // filter to only an 'equal' seat by row/column
                .findFirst() // get that seat container (Optional that may contain seat or not)
                .orElse(null); // if object exists, return it, otherwise return null

        return foundSeat;
    }

    public String generateUniqueToken() {
        return UUID.randomUUID().toString();
    }

    public Seat unbookSeat(Seat seatToReturn) {
        seatToReturn.setStatus(Seat.STATUS.AVAILABLE);
        seatToReturn.setToken(null);
        return seatToReturn;
    }

    public Seat findSeatByToken(String token) {
        Seat foundSeat = cinemaTheater.getSeats().stream()
                .filter(s -> token.equals(s.getToken())) // filter to only an 'equal' seat by token
                .findFirst() // get that seat container (Optional that may contain seat or not)
                .orElse(null); // if object exists, return it, otherwise return null

        return foundSeat;
    }

    public boolean checkPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    public int getCurrentIncome() {
        return cinemaTheater.getSeats().stream()
                .filter(s -> s.getStatus() == Seat.STATUS.PURCHASED) // filter to only purchased seats
                .mapToInt(Seat::getPrice)
                .sum();
    }

    public int getAvailableSeatsCount() {
        return (int) cinemaTheater
                .getSeats()
                .stream()
                .filter(seat -> seat.getStatus() == Seat.STATUS.AVAILABLE)
                .count();
    }

    public int getPurchasedTickets() {
        return (int) cinemaTheater
                .getSeats()
                .stream()
                .filter(seat -> seat.getStatus() == Seat.STATUS.PURCHASED)
                .count();
    }
}
