package org.nekostudio.annotation;

import org.nekostudio.enums.Operation;

import java.lang.annotation.*;

/**
 * @author neko
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
  String name();

  String description() default "";

  Operation type();
}
