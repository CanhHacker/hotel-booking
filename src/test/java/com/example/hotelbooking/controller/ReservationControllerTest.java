package com.example.hotelbooking.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;


import com.example.hotelbooking.controllers.ReservationController;
import com.example.hotelbooking.dtos.ReservationDto;
import com.example.hotelbooking.entities.Reservation;
import com.example.hotelbooking.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    private Reservation reservation;
    private ReservationDto reservationDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartDate(Instant.parse("2021-07-19T16:00:00Z"));
        reservation.setEndDate(Instant.parse("2021-07-20T16:00:00Z"));
        reservation.setPrice(500.0);
        // Set other fields like customer and hotel

        reservationDto = ReservationDto.ofEntity(reservation, false);
    }

    @Test
    public void testGetReservationById() throws Exception {
        when(reservationService.getReservationById(anyLong())).thenReturn(reservation);

        mockMvc.perform(get("/reservation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.startDate").value(reservationDto.getStartDate().toString()))
                .andExpect(jsonPath("$.endDate").value(reservationDto.getEndDate().toString()))
                .andExpect(jsonPath("$.price").value(reservationDto.getPrice()));
    }
}
