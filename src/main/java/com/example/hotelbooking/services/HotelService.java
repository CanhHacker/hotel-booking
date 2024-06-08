package com.example.hotelbooking.services;

import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.entities.Location;
import com.example.hotelbooking.repositories.HotelRepository;
import com.example.hotelbooking.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Hotel> getAllHotelInDestination(Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found"));
        return hotelRepository.findByLocation(location);
    }

}
