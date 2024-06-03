package com.nvhien.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEntity {
    private int code;
    private String body;
    private String contentType;
}
