package com.nvhien.handler;

import com.nvhien.entity.ResponseEntity;
import com.sun.net.httpserver.HttpExchange;

public interface IBookingHandler {
    ResponseEntity execute(HttpExchange exchange);
}
