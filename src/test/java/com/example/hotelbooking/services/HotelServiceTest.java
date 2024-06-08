package com.example.hotelbooking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.entities.Location;
import com.example.hotelbooking.repositories.HotelRepository;
import com.example.hotelbooking.repositories.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private HotelRepository hotelRepository;

    private Location location;
    private List<Hotel> hotels;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        location = new Location();
        location.setId(1L);
        location.setCity("Sample Location");

        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setHotelName("Hotel 1");
        hotel1.setLocation(location);

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setHotelName("Hotel 2");
        hotel2.setLocation(location);
        hotels = Arrays.asList(hotel1, hotel2);
    }

    @Test
    public void testGetAllHotelInDestination_Success() {
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
        when(hotelRepository.findByLocation(any(Location.class))).thenReturn(hotels);
        List<Hotel> result = hotelService.getAllHotelInDestination(1L);
        assertEquals(2, result.size());
        assertEquals("Hotel 1", result.get(0).getHotelName());
        assertEquals("Hotel 2", result.get(1).getHotelName());
    }

    @Test
    public void testGetAllHotelInDestination_LocationNotFound() {
        when(locationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> hotelService.getAllHotelInDestination(1L));
    }
}
