package com.nvhien.service;

import com.nvhien.entity.HotelResponse;
import com.nvhien.entity.PaginationObj;

public interface IHotelService {
    PaginationObj<HotelResponse> findByLocation(String location, int startIndex, int pageSize);
}
