package com.spring_boot_security_jwt_auth_example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_security_jwt_auth_example.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
