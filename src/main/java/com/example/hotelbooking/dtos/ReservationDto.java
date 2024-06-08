package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Customer;
import com.example.hotelbooking.entities.Hotel;
import com.example.hotelbooking.entities.Reservation;
import com.example.hotelbooking.entities.Room;
import com.example.hotelbooking.entities.Section;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ReservationDto {
    private Instant startDate;
    private Instant endDate;
    private Double price;
    private RoomDto roomDto;
    private HotelDto hotelDto;
    private CustomerDto customerDto;
    private SectionDto sectionDto;

    public static ReservationDto ofEntity(Reservation reservation, boolean details) {
        ReservationDto res = new ReservationDto();
        BeanUtils.copyProperties(reservation, res, "id");
        if (reservation.getCustomer() != null) {
            res.setCustomerDto(CustomerDto.ofEntity(reservation.getCustomer(), false));
        }
        if (reservation.getHotel() != null) {
            res.setHotelDto(HotelDto.ofEntity(reservation.getHotel(), false));
        }
        if (reservation.getRoom() != null) {
            res.setRoomDto(RoomDto.ofEntity(reservation.getRoom(), false));
        }
        if (reservation.getSection() != null) {
            res.setSectionDto(SectionDto.ofEntity(reservation.getSection(), false));
        }
        return res;
    }

    public static List<ReservationDto> ofEntity(Collection<Reservation> reservations) {
        return reservations.stream().map(c -> ofEntity(c, false)).collect(Collectors.toList());
    }
}


