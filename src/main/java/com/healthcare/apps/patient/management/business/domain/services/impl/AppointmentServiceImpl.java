package com.healthcare.apps.patient.management.business.domain.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.healthcare.apps.patient.management.business.data.repositories.AppointmentRepository;
import com.healthcare.apps.patient.management.business.domain.services.AppointmentService;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AppointmentServiceImpl implements AppointmentService {

    @Inject
    AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentResponse> getAll() {
        return List.of();
    }

    @Override
    public Optional<AppointmentResponse> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        return null;
    }

    @Override
    public AppointmentResponse update(UUID id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
