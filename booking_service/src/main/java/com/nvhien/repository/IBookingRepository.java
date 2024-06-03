package com.nvhien.repository;

import com.nvhien.entity.BookingDAO;

import java.sql.SQLException;

public interface IBookingRepository {
    int create(BookingDAO bookingDAO) throws SQLException;
}
