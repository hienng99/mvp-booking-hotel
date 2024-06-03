package com.nvhien.repository;

import com.nvhien.entity.Hotel;

import java.sql.SQLException;
import java.util.List;

public interface IHotelRepository {
    List<Hotel> findByLocation(String location) throws SQLException;
}
