package com.eaii.service;

import com.eaii.domain.PaymentMethod;
import com.eaii.model.PaymentOrder;
import com.eaii.payload.dto.BookingDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDto userDto, BookingDto bookingDto, PaymentMethod paymentMethod) throws Exception;
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);
    PaymentLink  createRazorpayPaymentLink(UserDto userDto, Long amount, Long orderId) throws Exception;
    String createStripePaymentLink(UserDto userDto, Long amount, Long orderId) throws Exception;
}
