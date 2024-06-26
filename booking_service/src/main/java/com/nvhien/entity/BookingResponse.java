package com.nvhien.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookingResponse {
    private long id;
    private int customerId;
    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
    private String checkinTime;
    private String checkoutTime;
    private double totalAmount;
}
