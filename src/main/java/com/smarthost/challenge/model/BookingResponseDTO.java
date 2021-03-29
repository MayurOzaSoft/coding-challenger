package com.smarthost.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookingResponseDTO {
    private int economyRoomsUsage;
    private int premiumRoomsUsage;
    private int economyRoomsIncome;
    private int premiumRoomsIncome;
}
