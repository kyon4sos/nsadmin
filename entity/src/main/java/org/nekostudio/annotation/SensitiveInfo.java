package org.nekostudio.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.nekostudio.enums.SensitiveType;

import java.lang.annotation.*;

/**
 * @author neko
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = JacksonDesensitize.class)
public @interface SensitiveInfo {
   SensitiveType type();
}
