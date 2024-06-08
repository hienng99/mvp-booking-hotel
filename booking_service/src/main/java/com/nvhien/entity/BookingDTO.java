package com.nvhien.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class BookingDTO {
    private long id;
    private int customerId;
    private int hotelId;
    private Date checkinTime;
    private Date checkoutTime;
    private double totalAmount;
    private String hotelName;
    private String hotelAddress;
    private String hotelPhone;
}
