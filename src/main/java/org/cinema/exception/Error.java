package org.cinema.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {
    private int statusCode;
    private LocalDateTime timestamp;
    private String description;
    private String error;
}
