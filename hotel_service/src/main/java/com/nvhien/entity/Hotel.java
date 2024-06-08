package com.nvhien.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Hotel {
    private int id;
    private String name;
    private String address;
    private String phone;
}
