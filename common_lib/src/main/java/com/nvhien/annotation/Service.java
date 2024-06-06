package com.nvhien.annotation;

import dagger.MapKey;

@MapKey(unwrapValue = false)
public @interface Service {
    String pathPrefix() default "";
}
