package com.frog.system.annotation;

import java.lang.annotation.*;

/**
 * @author lh
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String title() default "";



}
