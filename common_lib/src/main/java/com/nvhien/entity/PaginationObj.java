package com.nvhien.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationObj<T> {
    private int total;
    private List<T> rows;
}
