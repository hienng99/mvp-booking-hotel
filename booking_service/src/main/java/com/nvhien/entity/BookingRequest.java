package com.nvhien.entity;

import lombok.Data;

@Data
public class BookingRequest {
    private int customerId;
    private int hotelId;
    private long checkinTime;
    private long checkoutTime;
}
