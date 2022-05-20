package com.bridgelabz.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class UserregistrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserregistrationApplication.class, args);
		log.info("User Registration App started");
	}
}
