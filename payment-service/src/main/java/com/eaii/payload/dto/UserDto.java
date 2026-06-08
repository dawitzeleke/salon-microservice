package com.eaii.payload.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;    
    private String name;
    private String email;

   public String getFullName() {
        return name;
    }
}
