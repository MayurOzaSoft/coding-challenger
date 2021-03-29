package com.smarthost.challenge.service.impl;

import com.smarthost.challenge.model.BookingRequestDTO;
import com.smarthost.challenge.model.BookingResponseDTO;
import com.smarthost.challenge.utility.TestUtility;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes =  BookingServiceImpl.class)
class BookingServiceImplTest {

    @MockBean
    private BookingServiceImpl bookingServiceImpl;

    @Test
    void bookingOccupancy() {
        BookingResponseDTO mockBookingResponseDTO = TestUtility.getMockBookingResponseDTO();
        BookingRequestDTO mockBookingRequestDTO = TestUtility.getMockBookingRequestDTO();

        when(bookingServiceImpl.bookingOccupancy(mockBookingRequestDTO)).thenReturn(mockBookingResponseDTO);

        BookingResponseDTO apiResponse = bookingServiceImpl.bookingOccupancy(mockBookingRequestDTO);

        assertThat(apiResponse).hasFieldOrProperty("economyRoomsUsage").isEqualTo(mockBookingResponseDTO);
        assertThat(apiResponse).hasFieldOrProperty("premiumRoomsIncome").isEqualTo(mockBookingResponseDTO);
        assertEquals(apiResponse.getPremiumRoomsUsage(), mockBookingResponseDTO.getPremiumRoomsUsage());
    }
}