package org.cinema.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CinemaTheatreExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String PASSWORD_IS_WRONG = "The password is wrong!";

    @ExceptionHandler(SeatBookingException.class)
    public ResponseEntity<Error> handleSeatBookingException(SeatBookingException e, WebRequest request
    ) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getDescription(false),
                e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GetStatsException.class)
    public ResponseEntity<Error> handleGetStatsException(GetStatsException e, WebRequest request
    ) {
        Error error = new Error(HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                request.getDescription(false),
                e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Error error = new Error(HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                request.getDescription(false),
                PASSWORD_IS_WRONG);

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
