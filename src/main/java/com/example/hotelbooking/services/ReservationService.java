package com.example.hotelbooking.services;

import com.example.hotelbooking.entities.Reservation;
import com.example.hotelbooking.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Reservation createOrEditBooking(Reservation reservation, Long id) {
        if(id == null){
            return reservationRepository.save(reservation);
        }
        else{
            Reservation reservation1 = reservationRepository.getById(id);
            reservation1.setStartDate(reservation.getStartDate());
            reservation1.setEndDate(reservation.getEndDate());
            reservation1.setPrice(reservation.getPrice());
            reservation1.setCustomer(reservation.getCustomer());
            reservation1.setHotel(reservation.getHotel());
            reservation1.setSection(reservation.getSection());
            reservation1.setRoom(reservation.getRoom());
            return reservationRepository.save(reservation1);
        }
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.getById(id);
    }
}
