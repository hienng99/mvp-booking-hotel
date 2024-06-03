package com.nvhien.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Booking {
    private int bookingId;
    private int customerId;
    private int hotelId;
    private Date checkinTime;
    private Date checkoutTime;
    private Date createTime;
    private Date updateTime;
}
