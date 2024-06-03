package com.nvhien.service;

import com.nvhien.entity.BookingRequest;

public interface IBookingService {
    boolean create(BookingRequest bookingRequest);
}
