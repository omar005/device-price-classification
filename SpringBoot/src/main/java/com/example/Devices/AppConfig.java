package com.example.Devices;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for defining beans.
 */
@Configuration
public class AppConfig {

    /**
     * Configures and provides a RestTemplate bean.
     *
     * @param builder RestTemplateBuilder for building RestTemplate instances.
     * @return RestTemplate instance.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
