package com.spring_boot_security_jwt_auth_example.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_boot_security_jwt_auth_example.dto.RegisterRequest;
import com.spring_boot_security_jwt_auth_example.entity.User;
import com.spring_boot_security_jwt_auth_example.exception.EmailAlreadyExistsException;
import com.spring_boot_security_jwt_auth_example.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void register(RegisterRequest request) {
		boolean existsByEmail = this.userRepository.existsByEmail(request.email());

		if (existsByEmail) {
			throw new EmailAlreadyExistsException(request.email());
		}

		User user = new User();
		user.setEmail(request.email());
		user.setPassword(this.passwordEncoder.encode(request.password()));

		this.userRepository.save(user);

	}
}
