package com.nvhien.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Builder
public class HotelDTO implements Comparable<HotelDTO> {
    private int id;
    private String name;
    private String address;
    private String phone;
    private double matchingLocationProb;

    @Override
    public int compareTo(@NotNull HotelDTO o) {
        return Double.compare(this.matchingLocationProb, o.matchingLocationProb);
    }
}
