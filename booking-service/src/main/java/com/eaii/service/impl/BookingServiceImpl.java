package com.eaii.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.eaii.domain.BookingStatus;
import com.eaii.model.Booking;
import com.eaii.model.SalonReport;
import com.eaii.payload.dto.BookingRequest;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.repository.BookingRepository;
import com.eaii.service.BookingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDto user, SalonDto salon, Set<ServiceDto> serviceDtoSet) {
        int totalDuration = serviceDtoSet.stream().mapToInt(ServiceDto::getDuration).sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime) throws Exception {
        
        List<Booking> existingBookings = getBookingsBySalon(salonDto.getId());
        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDto.getCloseTime().atDate(bookingEndTime.toLocalDate());

        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
           throw new Exception("Booking time is outside of salon operating hours.");
        }

        for(Booking existingBooking: existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Time slot is not available.");
            }
            
        }
        return true; // Time slot is available

    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getBookingsByCustomer'");
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingsBySalon'");
    }

    @Override
    public Booking getBookingById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingById'");
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBooking'");
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDateTime date, Long salonId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingsByDate'");
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSalonReport'");
    }
    
}
