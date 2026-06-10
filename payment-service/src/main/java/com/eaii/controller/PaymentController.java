package com.eaii.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.domain.PaymentMethod;
import com.eaii.model.PaymentOrder;
import com.eaii.payload.dto.BookingDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.payload.response.PaymentLinkResponse;
import com.eaii.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDto booking, @RequestParam PaymentMethod paymentMethod) throws StripeException, RazorpayException {
        // In a real application, you would fetch the user details from the security context or database
        UserDto user = new UserDto();
        user.setId(1L); // Example user ID
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        PaymentLinkResponse res = paymentService.createOrder(user, booking, paymentMethod);
        return ResponseEntity.ok(res);
       
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId) throws Exception {
        
        PaymentOrder res = paymentService.getPaymentOrderById( paymentOrderId);
        return ResponseEntity.ok(res);
       
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPaymentOrder(@RequestParam String paymentId, @RequestParam String paymentLinkId) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId( paymentLinkId);
        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
        return ResponseEntity.ok(res);
       
    }

    
}
