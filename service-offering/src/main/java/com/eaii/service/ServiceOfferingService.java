package com.eaii.service;

import java.util.List;
import java.util.Set;

import com.eaii.model.ServiceOffering;
import com.eaii.payload.dto.CategoryDto;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;

public interface ServiceOfferingService {
    ServiceOffering createService(SalonDto salonDto, ServiceDto serviceDto, CategoryDto categoryDto);
    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId);
    List<ServiceOffering> getServicesByIds(Set<Long> ids);
    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;
    ServiceOffering getServiceById(Long serviceId) throws Exception;
}