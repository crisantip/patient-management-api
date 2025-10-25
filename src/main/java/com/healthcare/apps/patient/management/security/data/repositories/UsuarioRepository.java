package com.healthcare.apps.patient.management.security.data.repositories;

import java.util.Optional;

import com.healthcare.apps.patient.management.security.data.entities.UsuarioEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<UsuarioEntity, Integer> {

	public Optional<UsuarioEntity> findByUsername(String username) {
		return find("lower(username) = lower(?1)", username).firstResultOptional();
	}
}
