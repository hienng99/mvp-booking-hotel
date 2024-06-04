package com.nvhien.repository;

import com.nvhien.entity.Booking;

public interface IBookingRepository {
    int create(Booking booking);

    Booking getById(long id);
}
