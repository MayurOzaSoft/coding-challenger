package com.smarthost.challenge.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class BookingErrorDTO {
    private String message;
    private HttpStatus httpStatus;
    private Integer httpStatusCode;
}
