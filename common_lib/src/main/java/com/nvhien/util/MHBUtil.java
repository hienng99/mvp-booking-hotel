package com.nvhien.util;


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
        return queryParam.get(name);
    }

    public static void writeResponse(HttpExchange exchange, String response) {
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("Error writing response: {}", e.getMessage());
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
}
