package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Room;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RoomDto {
    private Integer floor;
    private HotelDto hotelDto;
    private SectionDto sectionDto;

    public static RoomDto ofEntity(Room room, boolean b) {
        RoomDto res = new RoomDto();
        BeanUtils.copyProperties(room, res, "id");
        if (room.getHotel() != null) {
            res.setHotelDto(HotelDto.ofEntity(room.getHotel(), false));
        }
        if (room.getSection() != null) {
            res.setSectionDto(SectionDto.ofEntity(room.getSection(), false));
        }
        return res;
    }
}
