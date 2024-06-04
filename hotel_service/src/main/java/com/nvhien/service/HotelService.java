package com.nvhien.service;

import com.nvhien.entity.Hotel;
import com.nvhien.repository.HotelRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class HotelService implements IHotelService {
    private final HotelRepository hotelRepository;

    @Inject
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> findByLocation(String location, int startIndex, int pageSize) {
        return hotelRepository.findByLocation(location, startIndex, pageSize);
    }
}
