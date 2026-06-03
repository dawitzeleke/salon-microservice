package com.eaii.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.model.ServiceOffering;
import com.eaii.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {
    
    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getAllServiceBySalonId(@PathVariable Long salonId, @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long serviceId) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingService.getServiceById(serviceId);
        return ResponseEntity.ok(serviceOffering);
    }
    
    @GetMapping("/list/{ids}")
    public ResponseEntity<List<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) {
        List<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(serviceOfferings);
    }

}
