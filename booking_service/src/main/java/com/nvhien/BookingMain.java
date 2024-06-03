package com.nvhien;

import com.nvhien.module.HandlerModule;
import com.nvhien.server.BookingHttpServer;
import dagger.Component;

import javax.inject.Singleton;
import java.io.IOException;

public class BookingMain {
    public static void main(String[] args) throws IOException {
        BookingComponent BookingComponent = DaggerBookingMain_BookingComponent.create();
        BookingComponent.bookingHttpServer().start();
    }

    @Singleton
    @Component(modules = HandlerModule.class)
    interface BookingComponent {
        BookingHttpServer bookingHttpServer();
    }
}