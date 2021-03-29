package com.smarthost.challenge.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class BookingControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void bookingManipulation_I() throws Exception {
        mockMvc.perform(
                get("/booking-occupancy")
                        .param("availablePremiumRooms", "3")
                        .param("availableEconomyRooms", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premiumRoomsUsage", is(3)))
                .andExpect(jsonPath("$.economyRoomsUsage", is(3)))
                .andExpect(jsonPath("$.premiumRoomsIncome", is(738)))
                .andExpect(jsonPath("$.economyRoomsIncome", is(167)));
    }

    @Test
    public void bookingManipulation_II() throws Exception {
        mockMvc.perform(
                get("/booking-occupancy")
                        .param("availablePremiumRooms", "7")
                        .param("availableEconomyRooms", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premiumRoomsUsage", is(6)))
                .andExpect(jsonPath("$.economyRoomsUsage", is(4)))
                .andExpect(jsonPath("$.premiumRoomsIncome", is(1054)))
                .andExpect(jsonPath("$.economyRoomsIncome", is(189)));
    }

    @Test
    public void bookingManipulation_III() throws Exception {
        mockMvc.perform(
                get("/booking-occupancy")
                        .param("availablePremiumRooms", "2")
                        .param("availableEconomyRooms", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premiumRoomsUsage", is(2)))
                .andExpect(jsonPath("$.economyRoomsUsage", is(4)))
                .andExpect(jsonPath("$.premiumRoomsIncome", is(583)))
                .andExpect(jsonPath("$.economyRoomsIncome", is(189)));
    }

    @Test
    public void bookingManipulation_IV() throws Exception {
        mockMvc.perform(
                get("/booking-occupancy")
                        .param("availablePremiumRooms", "7")
                        .param("availableEconomyRooms", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.premiumRoomsUsage", is(7)))
                .andExpect(jsonPath("$.economyRoomsUsage", is(1)))
                .andExpect(jsonPath("$.premiumRoomsIncome", is(1153)))
                .andExpect(jsonPath("$.economyRoomsIncome", is(45)));
    }

}