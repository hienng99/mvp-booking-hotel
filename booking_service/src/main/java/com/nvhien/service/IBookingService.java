package com.nvhien.service;

import com.nvhien.entity.BookingRequest;
import com.nvhien.entity.BookingResponse;

public interface IBookingService {
    boolean create(BookingRequest bookingRequest);
    BookingResponse get(long id);
}
