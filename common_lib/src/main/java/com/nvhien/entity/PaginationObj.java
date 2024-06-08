package com.nvhien.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationObj<T> {
    private int total;
    private int pageSize;
    private List<T> rows;
}
