package com.nvhien.server;

import com.nvhien.entity.ResponseEntity;
import com.nvhien.handler.IBookingHandler;
import com.nvhien.module.RestApi;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Map;

@Singleton
@Log4j2
public class MessageHandler implements HttpHandler {
    private final Map<RestApi, IBookingHandler> handlers;

    @Inject
    public MessageHandler(Map<RestApi, IBookingHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        log.info("Receive request");
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        for (var entry : handlers.entrySet()) {
            RestApi api = entry.getKey();
            IBookingHandler handler = entry.getValue();
            if (api.method().name().equals(method) && api.path().equals(path)) {
                ResponseEntity responseEntity = handler.execute(exchange);
                exchange.getResponseHeaders().add("Content-Type", responseEntity.getContentType());
                String responseBody = responseEntity.getBody();
                exchange.sendResponseHeaders(responseEntity.getCode(), responseBody.length());
                MHBUtil.writeResponse(exchange, responseBody);
                exchange.close();
                return;
            }
        }

        exchange.sendResponseHeaders(405, 0);
        exchange.close();
    }
}