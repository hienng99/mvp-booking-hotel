package com.nvhien.service;

import com.nvhien.entity.Hotel;

import java.util.List;

public interface IHotelService {
    List<Hotel> findByLocation(String location, int startIndex, int pageSize);
}
