package com.nvhien.module;

import com.nvhien.annotation.RestApi;
import com.nvhien.enums.HttpMethod;
import com.nvhien.handler.GetHotelByLocationHandler;
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
    @RestApi(method = HttpMethod.GET, path = "/api/v1")
    static IHandler getHotelByLocationHandler(GetHotelByLocationHandler getHotelByLocationHandler) {
        return getHotelByLocationHandler;
    }

}
