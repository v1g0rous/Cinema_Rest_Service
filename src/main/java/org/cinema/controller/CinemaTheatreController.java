package org.cinema.controller;


import lombok.AllArgsConstructor;
import org.cinema.api.request.PurchaseTicketRequest;
import org.cinema.api.request.ReturnTicketRequest;
import org.cinema.api.response.GetSeatsResponse;
import org.cinema.api.response.GetStatsResponse;
import org.cinema.api.response.PurchaseTicketResponse;
import org.cinema.api.response.ReturnTicketResponse;
import org.cinema.entity.Seat;
import org.cinema.exception.GetStatsException;
import org.cinema.exception.SeatBookingException;
import org.cinema.service.PaymentService;
import org.cinema.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class CinemaTheatreController {
    public static final int UNDEFINED_PRICE = 0;
    private TicketService ticketService;
    private PaymentService paymentService;

    @GetMapping("/seats")
    public ResponseEntity<Object>  getSeats() {
        GetSeatsResponse response = new GetSeatsResponse();
        response.setTotalRows(ticketService.getCinemaTheater().getTotalRows());
        response.setTotalColumns(ticketService.getCinemaTheater().getTotalColumns());
        response.setSeats(ticketService.getAvailableSeats());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody PurchaseTicketRequest request) {
        Seat seat = Seat
                .builder()
                .row(request.getRow())
                .column(request.getColumn())
                .price(UNDEFINED_PRICE)
                .build();

        if (!ticketService.seatExists(seat)) {
            throw new SeatBookingException("The number of a row or a column is out of bounds!");
        }

        if (!ticketService.isSeatAvailable(seat)) {
            throw new SeatBookingException("The ticket has been already purchased!");
        }

        Seat bookedSeat = ticketService.bookSeat(seat);

        PurchaseTicketResponse responseBody = new PurchaseTicketResponse(
                bookedSeat.getToken(),
                bookedSeat);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody ReturnTicketRequest request) {
        if (request == null || request.getToken() == null) {
            throw new SeatBookingException("Wrong token!");
        }

        Seat seatToReturn = ticketService.findSeatByToken(request.getToken());

        if (seatToReturn == null) {
            throw new SeatBookingException("Wrong token!");
        }

        seatToReturn = ticketService.unbookSeat(seatToReturn);
        ReturnTicketResponse responseBody = new ReturnTicketResponse(seatToReturn);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam String password) {
        boolean passwordIsCorrect = ticketService.checkPasswordCorrect(password);

        if (!passwordIsCorrect) {
            throw new GetStatsException("The password is wrong!");
        }

        GetStatsResponse response = new GetStatsResponse();
        response.setCurrentIncome(ticketService.getCurrentIncome());
        response.setAvailableSeats(ticketService.getAvailableSeatsCount());
        response.setPurchasedTickets(ticketService.getPurchasedTickets());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
