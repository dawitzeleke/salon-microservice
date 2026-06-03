package com.eaii.model;

import lombok.Data;

@Data
public class SalonReport {
    private Long salonId;
    private String salonName;
    private Double totalEarnings;
    private int totalBookings;
    private int cancelledBookings;
    private Double totalRefund;
}
