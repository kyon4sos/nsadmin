package org.nekostudio.convation;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author neko
 */
public class LocalDateTimeConverter implements Converter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(Long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC+8")).toLocalDateTime();
    }
}
