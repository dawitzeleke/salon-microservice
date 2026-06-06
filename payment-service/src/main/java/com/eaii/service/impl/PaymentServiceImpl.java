package com.eaii.service.impl;

import org.springframework.stereotype.Service;

import com.eaii.domain.PaymentMethod;
import com.eaii.model.PaymentOrder;
import com.eaii.payload.dto.BookingDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.payload.response.PaymentLinkResponse;
import com.eaii.repository.ServiceRepository;
import com.eaii.service.PaymentService;
import com.razorpay.PaymentLink;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final ServiceRepository serviceRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecret;
    
    @Override
    public PaymentLinkResponse createOrder(UserDto userDto, BookingDto bookingDto, PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentOrderById'");
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentOrderByPaymentId'");
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDto userDto, Long amount, Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRazorpayPaymentLink'");
    }

    @Override
    public String createStripePaymentLink(UserDto userDto, Long amount, Long orderId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createStripePaymentLink'");
    }
    
}
