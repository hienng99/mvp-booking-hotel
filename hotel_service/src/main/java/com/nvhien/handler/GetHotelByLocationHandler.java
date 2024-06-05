package com.nvhien.handler;

import com.nvhien.common.HotelConst;
import com.nvhien.entity.HotelResponse;
import com.nvhien.entity.ResponseEntity;
import com.nvhien.itf.IHandler;
import com.nvhien.service.HotelService;
import com.nvhien.util.JsonUtil;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Log4j2
@Singleton
public class GetHotelByLocationHandler implements IHandler {
    private final HotelService hotelService;

    @Inject
    public GetHotelByLocationHandler(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public ResponseEntity execute(HttpExchange exchange) {
        String location = MHBUtil.getQueryParam(exchange, "location");
        try {
            String startIndexStr = MHBUtil.getQueryParam(exchange, "start");
            int startIndex = startIndexStr == null || StringUtils.isEmpty(startIndexStr) ? 0 : Integer.parseInt(startIndexStr);

            String pageSizeStr = MHBUtil.getQueryParam(exchange, "pageSize");
            int pageSize = pageSizeStr == null || StringUtils.isEmpty(pageSizeStr) ? HotelConst.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSizeStr);

            List<HotelResponse> hotelResponses = hotelService.findByLocation(location, startIndex, pageSize);
            if (hotelResponses == null) {
                return ResponseEntity.builder()
                        .code(500)
                        .contentType("application/json")
                        .build();
            }
            return ResponseEntity.builder()
                    .code(200)
                    .contentType("application/json")
                    .body(JsonUtil.objToJsonString(hotelResponses))
                    .build();
        } catch (NumberFormatException e) {
            log.error("Invalid query param");
            return ResponseEntity.builder()
                    .code(400)
                    .contentType("text/plain")
                    .body("Bad request")
                    .build();
        }
    }
}
