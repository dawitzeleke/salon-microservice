package com.eaii.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eaii.model.ServiceOffering;

public interface ServiceRepository extends JpaRepository<ServiceOffering, Long> {
    
    Set<ServiceOffering> findBySalonId(Long salonId);   
}
