package org.nekostudio.annotation;

import java.lang.annotation.*;

/**
 * @author neko
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {
    long frequency() default 60;
    long second() default 30;
}
