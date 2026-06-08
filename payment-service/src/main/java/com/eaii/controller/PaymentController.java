package com.eaii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.domain.PaymentMethod;
import com.eaii.payload.dto.BookingDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.payload.response.PaymentLinkResponse;
import com.eaii.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDto booking, @RequestParam PaymentMethod paymentMethod) {
        // In a real application, you would fetch the user details from the security context or database
        UserDto user = new UserDto();
        user.setId(1L); // Example user ID
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        return null;
       
    }
}
