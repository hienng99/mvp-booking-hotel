package com.nvhien.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Booking {
    private long id;
    private int customerId;
    private int hotelId;
    private Date checkinTime;
    private Date checkoutTime;
    private double totalAmount;
}
