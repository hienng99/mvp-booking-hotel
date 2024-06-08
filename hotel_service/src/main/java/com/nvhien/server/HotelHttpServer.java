package com.nvhien.server;

import com.sun.net.httpserver.HttpServer;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Singleton
@Log4j2
public class HotelHttpServer {
    private static final int PORT = 8081;
    private final MessageHandler messageHandler;

    @Inject
    public HotelHttpServer(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/api/v1", messageHandler);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        log.warn("Server started on port {}", PORT);
    }
}
