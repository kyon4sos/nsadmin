package org.nekostudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author neko
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public CorsConfiguration corsConfigurer() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");
        configuration.addAllowedOrigin("*");
        return configuration;
    }
}