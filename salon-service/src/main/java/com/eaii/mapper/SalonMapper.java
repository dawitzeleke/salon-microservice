package com.eaii.mapper;

import com.eaii.model.Salon;
import com.eaii.payload.dto.SalonDto;

public class SalonMapper {
    public static SalonDto mapToDto(Salon salon) {
        if (salon == null) {
            return null;
        }
        SalonDto dto = new SalonDto();
        dto.setId(salon.getId());
        dto.setName(salon.getName());
        dto.setImages(salon.getImages());
        dto.setAddress(salon.getAddress());
        dto.setPhoneNumber(salon.getPhoneNumber());
        dto.setEmail(salon.getEmail());
        dto.setCity(salon.getCity());
        dto.setOwnerId(salon.getOwnerId());
        dto.setOpenTime(salon.getOpenTime());
        dto.setCloseTime(salon.getCloseTime());
        return dto;
    }
}
