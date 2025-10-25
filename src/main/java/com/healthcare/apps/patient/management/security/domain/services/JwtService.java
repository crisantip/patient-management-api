package com.healthcare.apps.patient.management.security.domain.services;

import com.healthcare.apps.patient.management.security.data.entities.UsuarioEntity;

public interface JwtService {

	String generateToken(UsuarioEntity entity);
}
