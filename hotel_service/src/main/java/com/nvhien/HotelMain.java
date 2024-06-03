package com.nvhien;

import com.nvhien.module.HandlerModule;
import com.nvhien.server.HotelHttpServer;
import dagger.Component;

import javax.inject.Singleton;
import java.io.IOException;

public class HotelMain {
    public static void main(String[] args) throws IOException {
        HotelComponent hotelComponent = DaggerHotelMain_HotelComponent.create();
        hotelComponent.hotelHttpServer().start();
    }

    @Singleton
    @Component(modules = HandlerModule.class)
    interface HotelComponent {
        HotelHttpServer hotelHttpServer();
    }
}