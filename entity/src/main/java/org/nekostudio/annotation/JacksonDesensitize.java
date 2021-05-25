package org.nekostudio.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.nekostudio.util.SensitivenUtil;

import java.io.IOException;

/**
 * @author neko
 */
public class JacksonDesensitize extends JsonSerializer<String> implements ContextualSerializer{

    private SensitiveInfo sensitiveInfo;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        if (!StringUtils.hasText(s)) {
//            return;
//        }
        String res ="";
        if (sensitiveInfo!=null) {

            res = SensitivenUtil.handler(sensitiveInfo.type(),s);
        }
        jsonGenerator.writeString(res);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        SensitiveInfo annotation = beanProperty.getAnnotation(SensitiveInfo.class);
        if (annotation!=null) {
            sensitiveInfo = annotation;
        }
        return this;
    }
}
