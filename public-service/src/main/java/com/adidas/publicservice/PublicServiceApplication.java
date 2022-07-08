package com.adidas.publicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Public Service API",
		description = "Public Service API",
		version = "1.0",
		contact = @Contact(name = "Adidas", url = "https://adidas.com")))
public class PublicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicServiceApplication.class, args);
	}

}
