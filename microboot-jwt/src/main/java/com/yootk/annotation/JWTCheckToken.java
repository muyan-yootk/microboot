package com.yootk.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 该注解主要用于方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface JWTCheckToken { // JWT的检查注解
    public boolean required() default true; // 是否要启用Token检查
}
