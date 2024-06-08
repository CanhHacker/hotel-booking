package com.example.hotelbooking.controllers;


import com.example.hotelbooking.dtos.HotelDto;
import com.example.hotelbooking.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/{locationId}")
    public List<HotelDto> getAllHotelInDestination(@PathVariable Long locationId){
        return HotelDto.ofEntity(hotelService.getAllHotelInDestination(locationId));
    }
}
