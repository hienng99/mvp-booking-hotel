package com.nvhien.handler;

import com.nvhien.entity.ResponseEntity;
import com.nvhien.itf.IHandler;
import com.nvhien.service.BookingService;
import com.nvhien.util.JsonUtil;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;

@Log4j2
@Singleton
public class BookingGetByIdHandler implements IHandler {
    private final BookingService service;

    @Inject
    public BookingGetByIdHandler(BookingService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity execute(HttpExchange exchange) {
        try {
            long id = Long.parseLong(MHBUtil.getQueryParam(exchange, "id"));
            return ResponseEntity.builder()
                    .code(200)
                    .contentType("application/json")
                    .body(JsonUtil.objToJsonString(service.get(id)))
                    .build();
        } catch (Exception exception) {
            return ResponseEntity.builder()
                    .code(500)
                    .contentType("application/json")
                    .build();
        }
    }
}
