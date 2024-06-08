package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Section;
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
public class SectionDto {
    private String roomType;

    public static SectionDto ofEntity(Section section, boolean b) {
        SectionDto res = new SectionDto();
        BeanUtils.copyProperties(section, res, "id");
        return res;
    }
}
