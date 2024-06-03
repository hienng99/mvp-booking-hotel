package com.nvhien.module;

import com.nvhien.enums.HttpMethod;
import com.nvhien.handler.CreateBookingBookingHandler;
import com.nvhien.handler.IBookingHandler;
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
    static IBookingHandler createBookingHandler(CreateBookingBookingHandler createBookingHandler) {
        return createBookingHandler;
    }

}
