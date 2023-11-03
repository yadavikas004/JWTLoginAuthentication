package com.jwt.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtLoginAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtLoginAuthenticationApplication.class, args);
		System.out.println("--------------------Application Started--------------------");
	}

}
