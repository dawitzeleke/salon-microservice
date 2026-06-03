package com.eaii.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eaii.model.ServiceOffering;
import com.eaii.payload.dto.CategoryDto;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;
import com.eaii.repository.ServiceRepository;
import com.eaii.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceRepository serviceRepository;

    @Override
    public ServiceOffering createService(SalonDto salonDto, ServiceDto serviceDto, CategoryDto categoryDto) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceDto.getName());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setPrice(serviceDto.getPrice());
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setSalonId(salonDto.getId());
        serviceOffering.setCategoryId(categoryDto.getId());
        serviceOffering.setDuration(serviceDto.getDuration());
        return serviceRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        
        ServiceOffering serviceOffering = serviceRepository.findById(serviceId).orElse(null);
        if (serviceOffering == null) {
            throw new Exception("Service not found with id: " + serviceId);
        }
        serviceOffering.setName(service.getName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setImage(service.getImage());
        serviceOffering.setDuration(service.getDuration());
        return serviceRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> services = serviceRepository.findBySalonId(salonId);
        if (categoryId != null) {
            services = services.stream().filter(service -> service.getCategoryId() 
            != null && service.getCategoryId().equals(categoryId)).collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public List<ServiceOffering> getServicesByIds(Set<Long> ids) {

        return serviceRepository.findAllById(ids);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {
        return serviceRepository.findById(serviceId).orElseThrow(() -> new Exception("Service not found with id: " + serviceId));
    }
    
}
