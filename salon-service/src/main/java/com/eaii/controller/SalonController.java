package com.eaii.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.mapper.SalonMapper;
import com.eaii.model.Salon;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.service.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;
    
    @PostMapping("/")
    public ResponseEntity<SalonDto> createSalon(@RequestBody SalonDto salonDto) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.createSalon(salonDto, userDto);
        SalonDto salonDto1 = SalonMapper.mapToDto(salon);
        return ResponseEntity.ok(salonDto1);
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDto> updateSalon(@PathVariable Long salonId, @RequestBody SalonDto salonDto) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.updateSalon(salonDto, userDto, salonId);
        SalonDto salonDto1 = SalonMapper.mapToDto(salon);
        return ResponseEntity.ok(salonDto1);
    }

    @GetMapping("/")
    public ResponseEntity<List<SalonDto>> getAllSalons() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        List<Salon> salons = salonService.getAllSalons();
        List<SalonDto> salonDtos = salons.stream().map(SalonMapper::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(salonDtos);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.getSalonById(salonId);
        SalonDto salonDto = SalonMapper.mapToDto(salon);
        return ResponseEntity.ok(salonDto);
    }

     @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalons(@RequestParam String city) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        List<Salon> salons = salonService.searchSalonsByCity(city);
        List<SalonDto> salonDtos = salons.stream().map(SalonMapper::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(salonDtos);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDto> getSalonByOwnerId(@PathVariable Long ownerId) throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDto.getId());
        SalonDto salonDto = SalonMapper.mapToDto(salon);
        return ResponseEntity.ok(salonDto);
    }

}
