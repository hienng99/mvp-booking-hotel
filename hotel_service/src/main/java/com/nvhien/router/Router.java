package com.nvhien.router;

import com.nvhien.handler.GetHotelByLocationHandler;
import com.nvhien.handler.IHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class Router {
    private final Map<String, IHandler> getControllers = new HashMap<>();
    private final Map<String, IHandler> postControllers = new HashMap<>();
    private final Map<String, IHandler> putControllers = new HashMap<>();
    private final Map<String, IHandler> patchControllers = new HashMap<>();
    private final Map<String, IHandler> deleteControllers = new HashMap<>();

    @Inject
    public Router(GetHotelByLocationHandler hotelController) {
//        this.getControllers.put("/hotel", hotelController.findHotelByLocation());
    }

//    public void route(HttpExchange exchange) {
//        if (HttpMethod.GET.name().equals(exchange.getRequestMethod())
//                && this.getControllers.containsKey(exchange.getRequestURI())) {
//            this.getControllers.get(exchange.getRequestURI());
//        }
//        return null;
//    }
}
