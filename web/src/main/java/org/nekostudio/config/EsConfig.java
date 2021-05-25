package org.nekostudio.config;

import org.nekostudio.convation.LocalDateTimeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author neko
 */
@Component
public class EsConfig extends ElasticsearchConfigurationSupport {

    public LocalDateTimeConverter localDateTimeConverter() {
        return new LocalDateTimeConverter();
    }

    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        ArrayList<Converter> converters = new ArrayList<>();
        converters.add(localDateTimeConverter());
        return new ElasticsearchCustomConversions(converters);
    }
}


