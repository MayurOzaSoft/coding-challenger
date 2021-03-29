package com.smarthost.challenge.service.impl;

import com.smarthost.challenge.exception.BookingException;
import com.smarthost.challenge.model.BookingRequestDTO;
import com.smarthost.challenge.model.BookingResponseDTO;
import com.smarthost.challenge.service.BookingService;
import com.smarthost.challenge.utility.BookingUtility;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;

@Service
public class BookingServiceImpl implements BookingService {

    @Override
    public BookingResponseDTO bookingOccupancy(BookingRequestDTO bookingRequestDTO) {

        try{
            // 1. Read JSON
            List<Integer> guests = BookingUtility.readGuestJSON(bookingRequestDTO.getGuests());
            int availablePremiumRooms = bookingRequestDTO.getAvailablePremium();
            int availableEconomyRooms = bookingRequestDTO.getAvailableEconomy();

            // 2. separate out guest
            Map<Boolean, List<Integer>> separatedGuest = guests.stream().collect(partitioningBy(guest -> guest >= 100));
            List<Integer> premiumGuest = separatedGuest.get(true);
            List<Integer> economyGuest = separatedGuest.get(false);

            // 3. Premium guest manipulation
            int premiumRoomsUsage = Math.min(availablePremiumRooms, premiumGuest.size());
            int premiumRoomsIncome = premiumGuest.stream().limit(premiumRoomsUsage).mapToInt(Integer::intValue).sum();

            // 4. Upgrade Guest Manipulation
            int premiumRoomsLeft = Math.max(availablePremiumRooms - premiumRoomsUsage, 0);
            int economyRoomsLeft = Math.max(economyGuest.size() - availableEconomyRooms, 0);
            int upgradedRoomsUsage = Math.min(premiumRoomsLeft, economyRoomsLeft);
            int upgradedRoomsIncome = economyGuest.stream().limit(upgradedRoomsUsage).mapToInt(Integer::intValue).sum();

            // 5. Economy Guest Manipulation
            int economyRoomsUsage = Math.min(economyGuest.size() - upgradedRoomsUsage, availableEconomyRooms);
            int economyRoomsIncome = economyGuest.stream().skip(upgradedRoomsUsage).limit(economyRoomsUsage).mapToInt(Integer::intValue).sum();

            // 6. form final Output
            return BookingResponseDTO.builder()
                    .economyRoomsIncome(economyRoomsIncome)
                    .economyRoomsUsage(economyRoomsUsage)
                    .premiumRoomsIncome(premiumRoomsIncome + upgradedRoomsIncome)
                    .premiumRoomsUsage(premiumRoomsUsage + upgradedRoomsUsage)
                    .build();

        } catch(Exception e){
            throw new BookingException(e.getMessage());
        }
    }
}
