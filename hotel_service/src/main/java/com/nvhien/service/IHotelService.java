package com.nvhien.service;

import com.nvhien.entity.HotelResponse;

import java.util.List;

public interface IHotelService {
    List<HotelResponse> findByLocation(String location, int startIndex, int pageSize);
}
