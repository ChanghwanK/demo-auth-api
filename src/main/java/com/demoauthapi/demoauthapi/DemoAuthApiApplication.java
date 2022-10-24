package com.demoauthapi.demoauthapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class DemoAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAuthApiApplication.class, args);
	}

}
