package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HotelDto {
    private String hotelName;
    private LocationDto locationDto;

    public static HotelDto ofEntity(Hotel hotel, boolean b) {
        HotelDto res = new HotelDto();
        BeanUtils.copyProperties(hotel, res, "id");
        if (hotel.getLocation() != null) {
            res.setLocationDto(LocationDto.ofEntity(hotel.getLocation(), false));
        }
        return res;
    }

    public static List<HotelDto> ofEntity(Collection<Hotel> hotels) {
        return hotels.stream().map(c -> ofEntity(c, false)).collect(Collectors.toList());
    }
}
