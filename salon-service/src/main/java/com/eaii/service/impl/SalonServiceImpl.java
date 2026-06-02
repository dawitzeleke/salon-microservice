package com.eaii.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eaii.model.Salon;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.repository.SalonRepository;
import com.eaii.service.SalonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDto req, UserDto user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setPhoneNumber(req.getPhoneNumber());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        return salonRepository.save(salon);
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if (salon == null) {
            throw new Exception("Salon not found with id: " + salonId);
        }
        return salon;
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId).orElse(null);

        if (existingSalon != null && salon.getOwnerId().equals(user.getId())) {
            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setCity(salon.getCity());
            existingSalon.setImages(salon.getImages());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());
            existingSalon.setOwnerId(user.getId());

            return salonRepository.save(existingSalon);
        }
        throw new Exception("Salon not found with id: " + salonId);

    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        Salon salons = salonRepository.findByOwnerId(ownerId);
        return salons;
    }

    @Override
    public List<Salon> searchSalonsByCity(String city) {
        return salonRepository.searchSalons(city);
    }

    @Override
    public void deleteSalon(Long salonId, UserDto user) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if (existingSalon != null && existingSalon.getOwnerId().equals(user.getId())) {
            salonRepository.delete(existingSalon);
        } else {
            throw new Exception("Salon not found with id: " + salonId);
        }
    }

}
