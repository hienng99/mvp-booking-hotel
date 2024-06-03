package com.nvhien.module;

import com.nvhien.enums.HttpMethod;
import com.nvhien.handler.GetHotelByLocationHandler;
import com.nvhien.handler.IHandler;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Singleton;

@Module
public abstract class HandlerModule {
    @Provides
    @IntoMap
    @Singleton
    @RestApi(method = HttpMethod.GET, path = "/api/hotel")
    static IHandler getHotelByLocationHandler(GetHotelByLocationHandler getHotelByLocationHandler) {
        return getHotelByLocationHandler;
    }

}
