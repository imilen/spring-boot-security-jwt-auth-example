package com.spring_boot_security_jwt_auth_example.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	public EmailAlreadyExistsException(String email) {
		super("Email already in use: " + email);
	}
}
