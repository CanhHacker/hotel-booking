package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Location;
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
public class LocationDto {
    private String city;

    public static LocationDto ofEntity(Location location, boolean b) {
        LocationDto res = new LocationDto();
        BeanUtils.copyProperties(location, res, "id");
        return res;
    }
}
