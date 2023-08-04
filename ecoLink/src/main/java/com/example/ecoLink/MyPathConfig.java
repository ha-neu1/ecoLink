package com.example.ecoLink;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyPathConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			registry.addResourceHandler("/banner/**").addResourceLocations("file:///c:/banner/");
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/kdt/upload/")
					.resourceChain(true);
			registry.addResourceHandler("/brand/**").addResourceLocations("file:///c:/brand/");
		} else if (os.contains("linux")) {
			registry.addResourceHandler("/banner/**").addResourceLocations("file:/usr/mydir/banner/");
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/kdt/upload/")
					.resourceChain(true);
			registry.addResourceHandler("/brand/**").addResourceLocations("file:/usr/mydir/brand/");
		} else {
			registry.addResourceHandler("/banner/**").addResourceLocations("file:///c:/banner/");
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/kdt/upload/")
					.resourceChain(true);
			registry.addResourceHandler("/brand/**").addResourceLocations("file:///c:/brand/");
		}

	}
}
