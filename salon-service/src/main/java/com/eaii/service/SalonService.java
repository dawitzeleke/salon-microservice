package com.eaii.service;

import java.util.List;

import com.eaii.model.Salon;
import com.eaii.payload.dto.UserDto;
import com.eaii.payload.dto.SalonDto;

public interface SalonService {
    Salon createSalon(SalonDto salon, UserDto user);
    Salon getSalonById(Long salonId) throws Exception;
    List<Salon> getAllSalons();
    Salon updateSalon(Long salonId, SalonDto salon, UserDto user) throws Exception;
    Salon getSalonByOwnerId(Long ownerId);
    List<Salon> searchSalonsByCity(String city);
    void deleteSalon(Long salonId, UserDto user) throws Exception;
}
