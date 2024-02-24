package com.inventory.appuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppuserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppuserServiceApplication.class, args);
	}

}
