package com.nvhien.server;

import com.nvhien.util.ConfigUtil;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Log4j2
@Singleton
public class BookingHttpServer {
    private final MessageHandler messageHandler;
    private final int port;

    @Inject
    public BookingHttpServer(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.port = 8080;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api", messageHandler);
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        log.warn("Server started on port {}", port);
    }
}
