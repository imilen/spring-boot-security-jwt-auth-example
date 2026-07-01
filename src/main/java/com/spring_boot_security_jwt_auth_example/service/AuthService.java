package com.spring_boot_security_jwt_auth_example.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring_boot_security_jwt_auth_example.dto.LoginRequest;
import com.spring_boot_security_jwt_auth_example.dto.LoginResponse;
import com.spring_boot_security_jwt_auth_example.dto.RegisterRequest;
import com.spring_boot_security_jwt_auth_example.entity.User;
import com.spring_boot_security_jwt_auth_example.exception.EmailAlreadyExistsException;
import com.spring_boot_security_jwt_auth_example.repository.UserRepository;
import com.spring_boot_security_jwt_auth_example.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

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

	public LoginResponse login(LoginRequest request) {

		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.email(),
				request.password());

		Authentication auth = this.authenticationManager.authenticate(authenticationToken);

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

		String accessToken = this.jwtService.generateToken(userDetails);

		return new LoginResponse(accessToken);
	}
}
