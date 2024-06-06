package com.nvhien.util;


import com.nvhien.entity.ResponseEntity;
import com.sun.net.httpserver.HttpExchange;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MHBUtil {
    public static Map<String, String> extractQueryParam(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public static String getQueryParam(HttpExchange exchange, String name) {
        Map<String, String> queryParam = extractQueryParam(exchange);
        if (queryParam == null) {
            return null;
        }
        return URLDecoder.decode(queryParam.get(name), StandardCharsets.UTF_8);
    }

    public static void sendResponse(HttpExchange exchange, ResponseEntity responseEntity) {
        exchange.getResponseHeaders().add("Content-Type", responseEntity.getContentType());
        if (responseEntity.getBody() == null) {
            try {
                exchange.sendResponseHeaders(responseEntity.getCode(), 0);
            } catch (IOException e) {
                log.error("Error when sending response.");
            }
            return;
        }
        byte[] responseBytes = responseEntity.getBody().getBytes(StandardCharsets.UTF_8);
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, responseBytes.length);
            os.write(responseBytes);
        } catch (IOException e) {
            log.error("Error when sending response.");
        }
    }

    public static JSONObject getJsonRequest(HttpExchange exchange) {
        try (InputStream is = exchange.getRequestBody()) {
            JSONParser jsonParser = new JSONParser();
            return (JSONObject) jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (ParseException parseException) {
            log.error("Error parsing request: {}", parseException.getMessage());
            return null;
        } catch (IOException ioException) {
            log.error("Error get json request: {}", ioException.getMessage());
            return null;
        }
    }

    public static String encodeQueryParam(HttpExchange exchange) {
        Map<String, String> queryParams = extractQueryParam(exchange);
        if (queryParams == null || queryParams.isEmpty()) {
            return null;
        }
        StringBuilder queryParamStr = new StringBuilder();
        queryParams.forEach((key, value) -> {
            queryParamStr.append(key).append("=").append(URLEncoder.encode(value, StandardCharsets.UTF_8)).append("&");
        });
        queryParamStr.deleteCharAt(queryParamStr.length() - 1);
        return queryParamStr.toString();
    }
}
