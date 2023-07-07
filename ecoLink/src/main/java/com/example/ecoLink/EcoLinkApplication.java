package com.example.ecoLink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
	


@SpringBootApplication
@Component
@ComponentScan(basePackages = {"controller","dto","dao","service"})
@MapperScan(basePackages = {"dao"})
public class EcoLinkApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EcoLinkApplication.class, args);
	}

}
