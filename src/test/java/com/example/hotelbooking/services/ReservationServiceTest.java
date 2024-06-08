package com.example.hotelbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;

import com.example.hotelbooking.entities.Reservation;
import com.example.hotelbooking.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartDate(Instant.parse("2021-07-19T16:00:00Z"));
        reservation.setEndDate(Instant.parse("2021-07-20T16:00:00Z"));
        reservation.setPrice(500.0);
        // Set other fields like customer, hotel, section, room if necessary
    }

    @Test
    public void testCreateOrEditBooking_CreateNew() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createOrEditBooking(reservation, null);

        assertEquals(reservation, result);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    public void testCreateOrEditBooking_EditExisting() {
        Reservation existingReservation = new Reservation();
        existingReservation.setId(1L);
        when(reservationRepository.getById(eq(1L))).thenReturn(existingReservation);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createOrEditBooking(reservation, 1L);

        assertEquals(reservation.getStartDate(), result.getStartDate());
        assertEquals(reservation.getEndDate(), result.getEndDate());
        assertEquals(reservation.getPrice(), result.getPrice());
        assertEquals(reservation.getCustomer(), result.getCustomer());
        assertEquals(reservation.getHotel(), result.getHotel());
        assertEquals(reservation.getSection(), result.getSection());
        assertEquals(reservation.getRoom(), result.getRoom());

        verify(reservationRepository).getById(1L);
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    public void testGetReservationById() {
        when(reservationRepository.getById(1L)).thenReturn(reservation);

        Reservation result = reservationService.getReservationById(1L);

        assertEquals(reservation, result);
        verify(reservationRepository).getById(1L);
    }
}

