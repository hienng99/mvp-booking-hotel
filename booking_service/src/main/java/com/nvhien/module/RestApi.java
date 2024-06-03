package com.nvhien.module;

import com.nvhien.enums.HttpMethod;
import dagger.MapKey;

@MapKey(unwrapValue = false)
public @interface RestApi {
    HttpMethod method() default HttpMethod.GET;
    String path() default "";
}
