package com.nvhien.server;

import com.nvhien.annotation.Service;
import com.nvhien.entity.ResponseEntity;
import com.nvhien.util.MHBUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Singleton
@Log4j2
public class MessageHandler implements HttpHandler {
    private static final HttpClient client = HttpClient.newHttpClient();
    private final Map<Service, String> servicesUrl;

    @Inject
    public MessageHandler(Map<Service, String> servicesUrl) {
        this.servicesUrl = servicesUrl;
    }

    @Override
    public void handle(HttpExchange exchange) {
        log.info("Receive request");
        String path = exchange.getRequestURI().getPath();
        for (var entry : servicesUrl.entrySet()) {
            String pathPrefix = entry.getKey().pathPrefix();
            String serviceUrl = entry.getValue();
            if (pathPrefix.equals(path)) {
                processRequest(exchange, serviceUrl);
                return;
            }
        }
        log.warn("Invalid url: {}", exchange.getRequestURI());
        try {
            exchange.sendResponseHeaders(404, 0);
        } catch (IOException e) {
            log.error("Exception when send response.");
        } finally {
            exchange.close();
        }
    }

    private void processRequest(HttpExchange exchange, String serviceUrl) {
        String uriStr = serviceUrl;
        if (MHBUtil.encodeQueryParam(exchange) != null) {
            uriStr += "?" + MHBUtil.encodeQueryParam(exchange);
        }
        URI uri = URI.create(uriStr);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .headers("Content-Type", "application/json")
                .uri(uri);


        switch (exchange.getRequestMethod()) {
            case "GET":
                requestBuilder.GET();
                break;
            case "POST":
                requestBuilder.POST(HttpRequest.BodyPublishers.ofInputStream(exchange::getRequestBody));
                break;
            case "PUT":
                requestBuilder.PUT(HttpRequest.BodyPublishers.ofInputStream(exchange::getRequestBody));
                break;
            case "DELETE":
                requestBuilder.DELETE();
        }
        try {
            HttpResponse<String> response = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            int code = response.statusCode();
            String contentType = response.headers().allValues("Content-Type").get(0);
            String body = response.body();
            ResponseEntity responseEntity = ResponseEntity.builder()
                    .code(code)
                    .contentType(contentType)
                    .body(body)
                    .build();
            MHBUtil.sendResponse(exchange, responseEntity);
        } catch (Exception exception) {
            log.error("Exception when send request to {}", uri);
            try {
                exchange.sendResponseHeaders(404, 0);
            } catch (IOException e) {
                log.error("Exception when send response.");
            }
        } finally {
            exchange.close();
        }

    }
}
