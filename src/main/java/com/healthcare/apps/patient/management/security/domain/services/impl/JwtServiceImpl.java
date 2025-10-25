package com.healthcare.apps.patient.management.security.domain.services.impl;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

import com.healthcare.apps.patient.management.security.data.entities.RolEntity;
import com.healthcare.apps.patient.management.security.data.entities.UsuarioEntity;
import com.healthcare.apps.patient.management.security.domain.services.JwtService;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

	@Override
	public String generateToken(UsuarioEntity entity) {
		Set<String> roles = entity.getRoles()
				.stream()
				.map(RolEntity::getName)
				.collect(Collectors.toSet());

		return Jwt.subject(entity.getUsername())
				.issuer("csip")
				.upn(entity.getUsername())
				.groups(roles)
				.issuedAt(System.currentTimeMillis() / 1000)
				.expiresIn(Duration.ofMinutes(5))
				.sign();
	}
}
