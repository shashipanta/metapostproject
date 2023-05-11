package com.meta.userpostproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfig implements WebMvcConfigurer {

    private static final String FILE_LOCATION = System.getProperty("user.home") + "/Desktop/META_FILE_STORE";

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(FILE_LOCATION);
    }
}
