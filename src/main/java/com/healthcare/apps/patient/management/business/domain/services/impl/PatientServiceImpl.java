package com.healthcare.apps.patient.management.business.domain.services.impl;

import java.util.Optional;

import com.healthcare.apps.patient.management.business.data.repositories.PatientRepository;
import com.healthcare.apps.patient.management.business.domain.mappers.PatientMapper;
import com.healthcare.apps.patient.management.business.domain.services.PatientService;
import com.healthcare.apps.patient.management.model.PatientRequest;
import com.healthcare.apps.patient.management.model.PatientResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    @Inject
    PatientRepository patientRepository;

    @Inject
    PatientMapper patientMapper;

    @Override
    public Optional<PatientResponse> getById(String id) {
        return Optional.ofNullable(patientRepository.findById(id))
                .map(patientMapper::toResponse);
    }

    @Override
    @Transactional
    public PatientResponse create(PatientRequest request) {
        return Optional.ofNullable(request)
                .filter(req -> patientRepository.findByIdOptional(req.getId()).isEmpty())
                .map(patientMapper::toEntity)
                .map(entity -> {
                    patientRepository.persist(entity);
                    return entity;
                })
                .map(patientMapper::toResponse)
                .orElseThrow(() -> new WebApplicationException("Paciente ya existe", Response.Status.CONFLICT));
    }
}