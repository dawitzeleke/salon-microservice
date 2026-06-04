package com.eaii.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Booking createBooking(BookingRequest booking, UserDto user, SalonDto salon, Set<ServiceDto> serviceDtoSet)
            throws Exception {
        int totalDuration = serviceDtoSet.stream().mapToInt(ServiceDto::getDuration).sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salon, bookingStartTime, bookingEndTime);

        int totalPrice = serviceDtoSet.stream().mapToInt(ServiceDto::getPrice).sum();
        Set<Long> serviceIds = serviceDtoSet.stream().map(ServiceDto::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setSalonId(salon.getId());
        newBooking.setCustomerId(user.getId());
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setServiceIds(serviceIds);

        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime)
            throws Exception {

        List<Booking> existingBookings = getBookingsBySalon(salonDto.getId());
        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDto.getCloseTime().atDate(bookingEndTime.toLocalDate());

        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time is outside of salon operating hours.");
        }

        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if (bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Time slot is not available.");
            }

            if (bookingStartTime.equals(existingBookingStartTime) || bookingEndTime.equals(existingBookingEndTime)) {
                throw new Exception("Time slot is not available, choose a different time.");
            }

        }
        return true; // Time slot is available

    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new Exception("Booking not found with id: " + id);
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new Exception("Booking not found with id: " + bookingId);
        }

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        List<Booking> bookings = getBookingsBySalon(salonId);

        if (date == null) {
            return bookings; // Return all bookings if date is not provided
        }

        if (bookings == null || bookings.isEmpty()) {
            return List.of(); // Return an empty list if there are no bookings
        }

        return bookings.stream()
                .filter(booking -> isSameDay(booking.getStartTime(), date) || isSameDay(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    private boolean isSameDay(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().equals(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings = getBookingsBySalon(salonId);
        int totalRevenue = (int) bookings.stream().mapToDouble(Booking::getTotalPrice).sum();
        int totalBookings = bookings.size();
        List<Booking> canceledBookings = bookings.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CANCELLED).collect(Collectors.toList());
        Double totalRefunds = canceledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();
        SalonReport report = new SalonReport();
        report.setSalonId(salonId);
        report.setTotalEarnings(totalRevenue);
        report.setTotalBookings(totalBookings);
        report.setTotalRefund(totalRefunds);
        report.setCancelledBookings(canceledBookings.size());


        return report;
    }

}
