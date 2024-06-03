package com.nvhien.handler;

import com.nvhien.entity.Hotel;
import com.nvhien.enums.HttpMethod;
import com.nvhien.module.RestApi;
import com.nvhien.service.HotelService;
import com.nvhien.util.JsonUtil;
import com.nvhien.util.MHBUtil;
import com.nvhien.entity.ResponseEntity;
import com.sun.net.httpserver.HttpExchange;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@RestApi(method = HttpMethod.GET, path = "/hotel")
@Singleton
public class GetHotelByLocationHandler implements IHandler {
    private final HotelService hotelService;

    @Inject
    public GetHotelByLocationHandler(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public ResponseEntity execute(HttpExchange exchange) {
        var queryParams = MHBUtil.extractQueryParam(exchange.getRequestURI().getQuery());
        String location = queryParams.get("location");
        List<Hotel> hotels = hotelService.findByLocation(location);
        return ResponseEntity.builder()
                .code(200)
                .contentType("application/json")
                .body(JsonUtil.objToJsonString(hotels))
                .build();
    }
}
