package com.eaii.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.eaii.domain.BookingStatus;
import com.eaii.model.Booking;
import com.eaii.model.SalonReport;
import com.eaii.payload.dto.BookingRequest;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;
import com.eaii.payload.dto.UserDto;

public interface BookingService {
    Booking createBooking(BookingRequest request, UserDto user, SalonDto salon, Set<ServiceDto> services);
    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);

    List<Booking> getBookingsByDate(LocalDateTime date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
