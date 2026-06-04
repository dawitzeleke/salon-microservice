package com.eaii.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaii.domain.BookingStatus;
import com.eaii.mapper.BookingMapper;
import com.eaii.model.Booking;
import com.eaii.model.SalonReport;
import com.eaii.payload.dto.BookingDto;
import com.eaii.payload.dto.BookingRequest;
import com.eaii.payload.dto.BookingSlotDto;
import com.eaii.payload.dto.SalonDto;
import com.eaii.payload.dto.ServiceDto;
import com.eaii.payload.dto.UserDto;
import com.eaii.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
 
    private final BookingService bookingService;
    @PostMapping()
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId, @RequestBody BookingRequest bookingRequest) throws Exception {
        UserDto user = new UserDto();
        user.setId(1L); // Simulate authenticated user

        SalonDto salon = new SalonDto();
        salon.setId(salonId); 

        salon.setOpenTime(LocalTime.of(9, 0));
        salon.setCloseTime(LocalTime.of(21, 0));
        Set<ServiceDto> serviceDtoSet = new HashSet<>(); 
    
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setPrice(399);
        serviceDto.setDuration(60);

        serviceDto.setName("Service 1");
        serviceDtoSet.add(serviceDto);

        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceDtoSet);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingsByCustomer() {
        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);
        return ResponseEntity.ok(convertToDtoSet(bookings));
    }

    private Set<BookingDto> convertToDtoSet(List<Booking> bookings) {
        
        return bookings.stream().map(booking -> {
            return BookingMapper.toDto(booking);
        }).collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingsBySalon() {
        List<Booking> bookings = bookingService.getBookingsBySalon(1L);
        return ResponseEntity.ok(convertToDtoSet(bookings));
    }

    @GetMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> getBookingsById(@PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long bookingId, @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDto>> getBookedSlot(@RequestParam(required = false) LocalDate date, @PathVariable Long salonId) {
        List<Booking> bookings = bookingService.getBookingsByDate(date, salonId);
        List<BookingSlotDto> slotsDtos = bookings.stream().map(bookng -> {
            BookingSlotDto slotDto = new BookingSlotDto();
            slotDto.setStartTime(bookng.getStartTime());
            slotDto.setEndTime(bookng.getEndTime());
            return slotDto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(slotsDtos);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(){
        SalonReport report = bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
    }
}
