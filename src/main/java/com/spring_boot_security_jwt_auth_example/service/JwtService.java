package com.spring_boot_security_jwt_auth_example.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.spring_boot_security_jwt_auth_example.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

	private final long accessTokenExpirationMs;
	private final SecretKey secretKey;

	public JwtService(JwtProperties jwt) {

		this.accessTokenExpirationMs = jwt.accessTokenExpirationMs();

		byte[] keyBytes = Decoders.BASE64.decode(jwt.secret());
		this.secretKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(UserDetails user) {
		Instant now = Instant.now();
		String uuid = UUID.randomUUID()
				.toString();
		Date expiration = Date.from(now.plusMillis(this.accessTokenExpirationMs));

		return Jwts.builder()
				.id(uuid)
				.subject(user.getUsername())
				.issuedAt(Date.from(now))
				.expiration(expiration)
				.signWith(this.secretKey, Jwts.SIG.HS256)
				.compact();

	}

	public Optional<Claims> parse(String token) {
		try {
			Claims claims = Jwts.parser()
					.verifyWith(this.secretKey)
					.build()
					.parseSignedClaims(token)
					.getPayload();

			return Optional.of(claims);
		} catch (JwtException | IllegalArgumentException e) {
			log.error(e.getMessage());
			return Optional.empty();
		}

	}

}
