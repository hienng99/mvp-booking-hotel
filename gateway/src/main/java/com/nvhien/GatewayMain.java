package com.nvhien;

import com.nvhien.module.ServiceModule;
import com.nvhien.server.GatewayHttpServer;
import dagger.Component;

import javax.inject.Singleton;
import java.io.IOException;

public class GatewayMain {
    public static void main(String[] args) throws IOException {
        GatewayComponent gatewayComponent = DaggerGatewayMain_GatewayComponent.create();
        gatewayComponent.gatewayHttpServer().start();
    }

    @Singleton
    @Component(modules = ServiceModule.class)
    interface GatewayComponent {
        GatewayHttpServer gatewayHttpServer();
    }
}
