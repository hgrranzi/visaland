package com.hgrranzi.visaland.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogUserAction {

    /**
     * @return Описание действия пользователя
     */
    String description() default "";

}

