package com.smarthost.challenge.utility;

import com.smarthost.challenge.model.BookingRequestDTO;
import com.smarthost.challenge.model.BookingResponseDTO;

public class TestUtility {

    private static final String guestJSON = "[23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]";

    public static BookingRequestDTO getMockBookingRequestDTO() {
        return BookingRequestDTO.builder()
                .availableEconomy(3)
                .availablePremium(3)
                .guests(guestJSON)
                .build();
    }

    public static BookingResponseDTO getMockBookingResponseDTO() {
        return BookingResponseDTO.builder()
                .economyRoomsIncome(167)
                .premiumRoomsIncome(738)
                .economyRoomsUsage(3)
                .premiumRoomsUsage(3)
                .build();
    }
}
