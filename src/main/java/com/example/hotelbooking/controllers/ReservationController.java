package com.example.hotelbooking.controllers;

import com.example.hotelbooking.dtos.ReservationDto;
import com.example.hotelbooking.entities.Reservation;
import com.example.hotelbooking.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;
    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable Long id){
        return ReservationDto.ofEntity(reservationService.getReservationById(id), false);
    }


    @RequestMapping(method = RequestMethod.PUT, value = {"/edit", "edit/{id}"})
    public ReservationDto createOrEditBooking(@PathVariable(required = false)Long id, @RequestBody Reservation reservation){
        return ReservationDto.ofEntity(reservationService.createOrEditBooking(reservation, id), false);
    }
}
