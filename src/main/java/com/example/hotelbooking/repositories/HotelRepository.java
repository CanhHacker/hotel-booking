package com.example.hotelbooking.repositories;

import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByLocation(Location  location);

}
