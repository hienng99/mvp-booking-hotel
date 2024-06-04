package com.nvhien.itf;

import com.nvhien.entity.ResponseEntity;
import com.sun.net.httpserver.HttpExchange;

public interface IHandler {
    ResponseEntity execute(HttpExchange exchange);
}
