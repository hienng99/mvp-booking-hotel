package com.nvhien.module;

import com.nvhien.annotation.Service;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;

@Module
public class ServiceModule {
    @Provides
    @IntoMap
    @Singleton
    @Service(pathPrefix = "/api/hotel")
    static String getHotelServiceUrl() {
        return "http://localhost:8081/api/v1";
    }

    @Provides
    @IntoMap
    @Singleton
    @Service(pathPrefix = "/api/booking")
    static String getBookingServiceUrl() {
        return "http://localhost:8082/api/v1";
    }
}
