package com.eaii.mapper;

import com.eaii.model.Booking;
import com.eaii.payload.dto.BookingDto;

public class BookingMapper {
    
    public static BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setSalonId(booking.getSalonId());
        dto.setCustomerId(booking.getCustomerId());
        dto.setStartTime(booking.getStartTime());
        dto.setEndTime(booking.getEndTime());
        dto.setServiceIds(booking.getServiceIds());
        dto.setStatus(booking.getStatus());
        dto.setTotalPrice(booking.getTotalPrice());
        return dto;
    }
}
