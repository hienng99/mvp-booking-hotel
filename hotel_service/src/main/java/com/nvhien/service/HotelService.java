package com.nvhien.service;

import com.nvhien.entity.Hotel;
import com.nvhien.entity.HotelResponse;
import com.nvhien.repository.HotelRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class HotelService implements IHotelService {
    private final HotelRepository hotelRepository;

    @Inject
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelResponse> findByLocation(String location, int startIndex, int pageSize) {
        List<Hotel> hotels = hotelRepository.findByLocation(location, startIndex, pageSize);
        List<HotelResponse> hotelResponses = new ArrayList<>();
        hotels.forEach(hotel -> {
            hotelResponses.add(HotelResponse.builder()
                    .id(hotel.getId())
                    .name(hotel.getName())
                    .address(hotel.getAddress())
                    .phone(hotel.getPhone())
                    .build());
        });
        return hotelResponses;
    }
}
