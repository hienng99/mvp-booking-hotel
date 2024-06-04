package com.nvhien.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HotelResponse {
    private int id;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
}
