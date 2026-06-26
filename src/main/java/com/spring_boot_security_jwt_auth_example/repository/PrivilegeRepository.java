package com.spring_boot_security_jwt_auth_example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_boot_security_jwt_auth_example.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Optional<Privilege> findByName(String name);

}
