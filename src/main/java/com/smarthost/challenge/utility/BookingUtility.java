package com.smarthost.challenge.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BookingUtility {
    public static final String guestJSON = "[23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]";

    public static List<Integer> readGuestJSON(String guests) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(guests);
        Integer[] object = objectMapper.readerFor(Integer[].class).readValue(jsonNode);
        List<Integer> guest = Arrays.asList(object);
        guest.sort(Comparator.comparingInt(b -> -b));
        return guest;
    }
}
