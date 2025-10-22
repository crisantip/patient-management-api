package com.healthcare.apps.patient.management.business.domain.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.healthcare.apps.patient.management.business.data.repositories.AppointmentRepository;
import com.healthcare.apps.patient.management.business.domain.mappers.AppointmentMapper;
import com.healthcare.apps.patient.management.business.domain.services.AppointmentService;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AppointmentServiceImpl implements AppointmentService {

    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentRepository
                .findAll()
                .stream()
                .map(appointmentMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<AppointmentResponse> getById(UUID id) {
        return Optional.ofNullable(appointmentRepository.findById(id))
                .map(appointmentMapper::toResponse);
    }

    @Override
    @Transactional
    public AppointmentResponse create(AppointmentRequest request) {
        return Optional.ofNullable(request)
                .map(appointmentMapper::toEntity)
                .map(entity -> {
                    entity.setDate(LocalDate.now());
                    appointmentRepository.persist(entity);
                    return entity;
                })
                .map(appointmentMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("La cita no debe ser null al crearlo."));
    }

    @Override
    public AppointmentResponse update(UUID id, AppointmentRequest request) {
        return null;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        appointmentRepository.deleteById(id);
    }
}
