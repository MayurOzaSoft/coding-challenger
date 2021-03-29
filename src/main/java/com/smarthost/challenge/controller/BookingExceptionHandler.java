package com.smarthost.challenge.controller;

import com.smarthost.challenge.exception.BookingException;
import com.smarthost.challenge.model.BookingErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleCSVFileNotFoundException(Exception ex) {
        return buildResponseEntity(BookingErrorDTO.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .httpStatusCode(HttpStatus.BAD_REQUEST.value()).build());
    }

    private ResponseEntity<Object> buildResponseEntity(BookingErrorDTO bookingErrorDTO) {
        return new ResponseEntity(bookingErrorDTO, bookingErrorDTO.getHttpStatus());
    }
}
