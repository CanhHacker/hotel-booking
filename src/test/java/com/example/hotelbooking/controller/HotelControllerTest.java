package com.example.hotelbooking.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.example.hotelbooking.controllers.HotelController;
import com.example.hotelbooking.dtos.HotelDto;
import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    private List<Hotel> hotels;
    private List<HotelDto> hotelDtos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setHotelName("Hotel 1");

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setHotelName("Hotel 2");

        hotels = Arrays.asList(hotel1, hotel2);
        hotelDtos = HotelDto.ofEntity(hotels);
    }

    @Test
    public void testGetAllHotelInDestination() throws Exception {
        when(hotelService.getAllHotelInDestination(anyLong())).thenReturn(hotels);

        mockMvc.perform(get("/hotel/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(hotelDtos.size()))
                .andExpect(jsonPath("$[0].hotelName").value(hotelDtos.get(0).getHotelName()))
                .andExpect(jsonPath("$[1].hotelName").value(hotelDtos.get(1).getHotelName()));
    }

    @SpringBootTest
    static
    class HotelbookingApplicationTests {

        @Test
        void contextLoads() {
        }

    }
}
