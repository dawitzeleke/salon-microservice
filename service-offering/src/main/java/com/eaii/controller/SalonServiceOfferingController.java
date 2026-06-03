package com.eaii.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.model.ServiceOffering;
import com.eaii.payload.dto.CategoryDto;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;
import com.eaii.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services-offering/salon-owner")
public class SalonServiceOfferingController {
    
    private final ServiceOfferingService serviceOfferingService;

    @PostMapping()
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDto serviceDto) {
        SalonDto salonDto = new SalonDto(); 
        salonDto.setId(1L);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(serviceDto.getCategory());

        ServiceOffering serviceOffering = serviceOfferingService.createService(salonDto, serviceDto, categoryDto);
        return ResponseEntity.ok(serviceOffering);
    }

    @PatchMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long serviceId, @RequestBody ServiceOffering service) throws Exception {
        
        ServiceOffering serviceOffering = serviceOfferingService.updateService(serviceId, service);
        return ResponseEntity.ok(serviceOffering);
    }
}
