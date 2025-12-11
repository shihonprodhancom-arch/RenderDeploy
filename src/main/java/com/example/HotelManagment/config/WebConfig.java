package com.example.HotelManagment.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve uploaded files from the file system
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");

        // If you're storing files in a different directory, adjust the path:
        // .addResourceLocations("file:/path/to/your/upload/directory/");
    }
}