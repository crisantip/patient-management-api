package com.healthcare.apps.patient.management.security.api.resources;

import com.healthcare.apps.patient.management.api.AuthApi;
import com.healthcare.apps.patient.management.model.AuthRequest;
import com.healthcare.apps.patient.management.security.domain.services.AuthService;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

public class AuthResource implements AuthApi {

	@Inject
	AuthService authService;

	@Override
	public Response authLogin(AuthRequest authRequest) {
		return authService.login(authRequest)
				.map(response -> Response.ok(response).build())
				.orElseGet(() -> Response.status(UNAUTHORIZED).build());
	}
}
