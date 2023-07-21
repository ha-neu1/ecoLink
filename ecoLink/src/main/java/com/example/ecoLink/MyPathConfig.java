package com.example.ecoLink;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPathConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/banner/**").addResourceLocations("file:///c:/banner/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/kdt/upload/");
	}
}
