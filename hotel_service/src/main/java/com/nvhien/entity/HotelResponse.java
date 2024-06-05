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
    private String address;
    private String phone;
}
