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
    private int hotelId;
    private String checkinTime;
    private String checkoutTime;
    private double totalAmount;
}
