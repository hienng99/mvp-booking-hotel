package com.nvhien.service;

import com.nvhien.entity.HotelDTO;
import com.nvhien.entity.HotelResponse;
import com.nvhien.entity.PaginationObj;
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
    public PaginationObj<HotelResponse> findByLocation(String location, int startIndex, int pageSize) {
        PaginationObj<HotelDTO> hotelDTOPaginationObj = hotelRepository.findByLocation(location, startIndex, pageSize);
        if (hotelDTOPaginationObj == null || hotelDTOPaginationObj.getRows() == null) {
            return null;
        }
        List<HotelResponse> hotelResponses = new ArrayList<>();
        hotelDTOPaginationObj.getRows().forEach(hotel -> hotelResponses.add(HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .phone(hotel.getPhone())
                .build()));
        return PaginationObj.<HotelResponse>builder()
                .total(hotelDTOPaginationObj.getTotal())
                .rows(hotelResponses)
                .build();
    }
}
