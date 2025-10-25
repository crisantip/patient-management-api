package com.healthcare.apps.patient.management.security.domain.services;

import java.util.Optional;

import com.healthcare.apps.patient.management.model.AuthRequest;
import com.healthcare.apps.patient.management.model.AuthResponse;

public interface AuthService {

	Optional<AuthResponse> login(AuthRequest request);
}
