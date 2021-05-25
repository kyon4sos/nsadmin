package org.nekostudio.annotation;

import java.lang.annotation.*;

/**
 * @author neko
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {
    int scope() default 0;
}
