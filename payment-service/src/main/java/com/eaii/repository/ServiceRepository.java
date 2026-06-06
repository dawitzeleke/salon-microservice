package com.eaii.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eaii.model.PaymentOrder;

public interface ServiceRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLinkId(String paymentLinkId);
}
