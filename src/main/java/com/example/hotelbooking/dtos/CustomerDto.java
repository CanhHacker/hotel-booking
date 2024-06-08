package com.example.hotelbooking.dtos;

import com.example.hotelbooking.entities.Customer;
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
public class CustomerDto {
    private String name;
    private String email;
    private String phoneNumber;
    private LocationDto locationDto;
    private String Gender;

    public static CustomerDto ofEntity(Customer customer, boolean b) {
        CustomerDto res = new CustomerDto();
        BeanUtils.copyProperties(customer, res, "id");
        if (customer.getLocation() != null) {
            res.setLocationDto(LocationDto.ofEntity(customer.getLocation(), false));
        }
       return res;
    }
}
