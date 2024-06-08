package com.nvhien.repository;

import com.nvhien.entity.HotelDTO;
import com.nvhien.entity.PaginationObj;

public interface IHotelRepository {
    PaginationObj<HotelDTO> findByLocation(String location, int offset, int limit);
}
