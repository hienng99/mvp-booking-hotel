package com.nvhien.module;

import com.nvhien.annotation.RestApi;
import com.nvhien.enums.HttpMethod;
import com.nvhien.handler.BookingCreateHandler;
import com.nvhien.handler.BookingGetByIdHandler;
import com.nvhien.itf.IHandler;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;

@Module
public abstract class HandlerModule {
    @Provides
    @IntoMap
    @Singleton
    @RestApi(method = HttpMethod.POST, path = "/api/booking")
    static IHandler createBookingHandler(BookingCreateHandler bookingCreateHandler) {
        return bookingCreateHandler;
    }

    @Provides
    @IntoMap
    @Singleton
    @RestApi(method = HttpMethod.GET, path = "/api/booking")
    static IHandler getBookingByIdHandler(BookingGetByIdHandler bookingGetByIdHandler) {
        return bookingGetByIdHandler;
    }
}
