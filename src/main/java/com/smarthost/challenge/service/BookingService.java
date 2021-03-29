package com.smarthost.challenge.service;

import com.smarthost.challenge.model.BookingRequestDTO;
import com.smarthost.challenge.model.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO bookingOccupancy(BookingRequestDTO bookingRequestDTO);
}
