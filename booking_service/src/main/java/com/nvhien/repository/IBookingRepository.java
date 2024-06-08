package com.nvhien.repository;

import com.nvhien.entity.BookingDTO;

public interface IBookingRepository {
    int create(BookingDTO bookingDTO);

    BookingDTO getById(long id);
}
