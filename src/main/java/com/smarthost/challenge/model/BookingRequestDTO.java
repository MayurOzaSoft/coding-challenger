package com.smarthost.challenge.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequestDTO {

    private int availablePremium;
    private int availableEconomy;
    private String guests;
}
