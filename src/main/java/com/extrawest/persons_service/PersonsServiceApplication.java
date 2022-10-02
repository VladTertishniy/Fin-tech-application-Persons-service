package com.extrawest.persons_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonsServiceApplication.class, args);
	}

}
