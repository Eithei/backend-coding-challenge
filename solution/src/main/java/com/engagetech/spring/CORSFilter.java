package com.engagetech.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Component
public class CORSFilter {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = getCorsConfigurationSource();
        return new CorsFilter(source);
    }

    private UrlBasedCorsConfigurationSource getCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = getCorsConfiguration();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    private CorsConfiguration getCorsConfiguration() {
        final CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setAllowedMethods(getAllowedMethods());

        return config;
    }

    private List<String> getAllowedMethods() {
        final List<String> allowedMethods = new ArrayList<>();

        allowedMethods.add("GET");
        allowedMethods.add("PUT");
        allowedMethods.add("POST");
        allowedMethods.add("DELETE");
        allowedMethods.add("OPTIONS");

        return allowedMethods;
    }
}
