package com.spring_boot_security_jwt_auth_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.spring_boot_security_jwt_auth_example.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class SpringBootSecurityJwtAuthExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtAuthExampleApplication.class, args);
	}

}
