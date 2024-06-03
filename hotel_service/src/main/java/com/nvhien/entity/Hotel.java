package com.nvhien.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Hotel {
    private int id;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\": \"" + name + '\"' +
                ", \"street\": \"" + street + '\"' +
                ", \"ward\": \"" + ward + '\"' +
                ", \"district\": \"" + district + '\"' +
                ", \"city\": \"" + city + '\"' +
                ", \"country\": \"" + country + '\"' +
                '}';
    }
}
