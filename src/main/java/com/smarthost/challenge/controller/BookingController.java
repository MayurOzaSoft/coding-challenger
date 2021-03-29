package com.smarthost.challenge.controller;

import com.smarthost.challenge.ChallengeApplication;
import com.smarthost.challenge.exception.BookingException;
import com.smarthost.challenge.model.BookingRequestDTO;
import com.smarthost.challenge.model.BookingResponseDTO;
import com.smarthost.challenge.service.BookingService;
import com.smarthost.challenge.utility.BookingUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@AllArgsConstructor
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(ChallengeApplication.class);


    private BookingService bookingService;

    @GetMapping(value = "/booking-occupancy")
    public ResponseEntity<BookingResponseDTO> bookingManipulation(
            @RequestParam("availablePremiumRooms") int availablePremiumRooms,
            @RequestParam("availableEconomyRooms") int availableEconomyRooms
            ){

        // validate params
        if(availablePremiumRooms <= 0 || availableEconomyRooms <= 0)
            throw new BookingException("Invalid Inputs, Enter positive non-zero value and execute again.");

        // Build request
        BookingRequestDTO bookingRequestDTO = BookingRequestDTO.builder()
                .availableEconomy(availableEconomyRooms)
                .availablePremium(availablePremiumRooms)
                .guests(BookingUtility.guestJSON)
                .build();

        // Call service
        BookingResponseDTO bookingResponseDTO = bookingService.bookingOccupancy(bookingRequestDTO);

        return ResponseEntity.ok(bookingResponseDTO);
    }
}
