package org.example.springbootstartersample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 自定义LOG注解
 * @Date 2025/4/15 10:26
 * @Created by raolongxiang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceLog {
    String value() default "";     // 业务标签
    boolean logParams() default true;  // 是否记录参数
    boolean logResult() default false; // 是否记录返回值
}