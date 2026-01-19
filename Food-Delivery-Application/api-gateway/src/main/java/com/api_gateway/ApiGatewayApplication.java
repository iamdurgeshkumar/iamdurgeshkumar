package com.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	public static void main(String[] args) {
		System.out.println(" api gateway started !! ");
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
