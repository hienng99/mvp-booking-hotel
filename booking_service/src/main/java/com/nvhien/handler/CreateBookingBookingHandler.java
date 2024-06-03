package com.nvhien.handler;

import com.nvhien.entity.Booking;
import com.nvhien.entity.BookingRequest;
import com.nvhien.entity.ResponseEntity;
import com.nvhien.service.BookingService;
import com.nvhien.util.JsonUtil;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

@Log4j2
@Singleton
public class CreateBookingBookingHandler implements IBookingHandler {
    private final BookingService service;

    @Inject
    public CreateBookingBookingHandler(BookingService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity execute(HttpExchange exchange) {
        JSONObject jsonRequest = MHBUtil.getJsonRequest(exchange);
        BookingRequest bookingRequest = JsonUtil.jsonStringToObj(jsonRequest.toJSONString(), BookingRequest.class);
        boolean isSuccess = service.create(bookingRequest);
        return ResponseEntity.builder()
                .contentType("application/json")
                .code(isSuccess ? 200 : 500)
                .build();
    }
}
