package com.example.simplecrudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SimpleCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrudApiApplication.class, args);
	}

}
