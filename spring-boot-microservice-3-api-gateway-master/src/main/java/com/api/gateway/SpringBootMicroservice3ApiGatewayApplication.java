package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableFeignClients
@EnableSwagger2WebMvc
@SpringBootApplication
public class SpringBootMicroservice3ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservice3ApiGatewayApplication.class, args);
	}

}
