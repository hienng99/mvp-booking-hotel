package com.nvhien.handler;

import com.nvhien.entity.HotelResponse;
import com.nvhien.entity.PaginationObj;
import com.nvhien.entity.ResponseEntity;
import com.nvhien.service.HotelService;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mockStatic;

public class GetHotelByLocationHandlerTest {
    GetHotelByLocationHandler handler;
    HotelService hotelService;
    PaginationObj<HotelResponse> hotelResponsePaginationObj = new PaginationObj<>();
    HttpExchange httpExchange;
    MockedStatic<MHBUtil> mockedMHBUtil;

    @Before
    public void setUp() {
        hotelService = Mockito.mock(HotelService.class);
        handler = new GetHotelByLocationHandler(hotelService);
        List<HotelResponse> hotels = new ArrayList<>();
        hotels.add(new HotelResponse(1, "Hotel 1", "City 1", "0123456789"));
        hotels.add(new HotelResponse(2, "Hotel 2", "City 2", "0987654321"));
        hotelResponsePaginationObj.setRows(hotels);
        mockedMHBUtil = mockStatic(MHBUtil.class);
        Mockito.when(MHBUtil.getQueryParam(httpExchange, "location")).thenReturn("abc");
        Mockito.when(MHBUtil.getQueryParam(httpExchange, "start")).thenReturn("0");
        Mockito.when(MHBUtil.getQueryParam(httpExchange, "pageSize")).thenReturn("5");
    }

    @After
    public void tearDown() {
        mockedMHBUtil.close();
    }

    @Test
    public void serviceReturnsHotels() {
        Mockito.when(hotelService.findByLocation("abc", 0, 5)).thenReturn(hotelResponsePaginationObj);
        ResponseEntity responseEntity = handler.execute(httpExchange);
        Assert.assertEquals(200, responseEntity.getCode());
    }

    @Test
    public void serviceReturnsNull() {
        Mockito.when(hotelService.findByLocation("abc", 0, 5)).thenReturn(null);
        ResponseEntity responseEntity = handler.execute(httpExchange);
        Assert.assertEquals(500, responseEntity.getCode());
    }
}