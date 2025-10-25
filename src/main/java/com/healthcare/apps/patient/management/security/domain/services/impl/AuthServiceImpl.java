package com.healthcare.apps.patient.management.security.domain.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

import com.healthcare.apps.patient.management.model.AuthRequest;
import com.healthcare.apps.patient.management.model.AuthResponse;
import com.healthcare.apps.patient.management.security.data.repositories.UsuarioRepository;
import com.healthcare.apps.patient.management.security.domain.services.AuthService;
import com.healthcare.apps.patient.management.security.domain.services.JwtService;

@Slf4j
@ApplicationScoped
public class AuthServiceImpl implements AuthService {

	@Inject
	UsuarioRepository usuarioRepository;
	@Inject
	JwtService jwtService;

	@Override
	public Optional<AuthResponse> login(AuthRequest request) {
		return usuarioRepository.findByUsername(request.getUserName())
				.filter(usuario -> BCrypt.verifyer()
						.verify(request.getPassword().toCharArray(), usuario.getPassword())
						.verified)
				.map(usuario -> {
					var token = jwtService.generateToken(usuario);
					return new AuthResponse().token(token);
				});
	}
}
