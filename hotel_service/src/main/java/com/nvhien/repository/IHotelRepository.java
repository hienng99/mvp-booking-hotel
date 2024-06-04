package com.nvhien.repository;

import com.nvhien.entity.Hotel;

import java.util.List;

public interface IHotelRepository {
    List<Hotel> findByLocation(String location, int offset, int limit);
}
