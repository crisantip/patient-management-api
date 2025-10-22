package com.healthcare.apps.patient.management.business.domain.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.healthcare.apps.patient.management.business.data.repositories.AppointmentRepository;
import com.healthcare.apps.patient.management.business.data.repositories.PatientRepository;
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
    PatientRepository patientRepository;

    @Inject
    AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentRepository
                .find("status", "ACTIVE")
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
        var patient = Optional.ofNullable(patientRepository.findById(request.getPatientId()))
                .orElseThrow(() -> new jakarta.ws.rs.NotFoundException("Paciente no encontrado: " + request.getPatientId()));
        
        return Optional.ofNullable(request)
                .map(appointmentMapper::toEntity)
                .map(entity -> {
                    entity.setPatient(patient);
                    entity.setDate(LocalDate.now());
                    entity.setStatus("ACTIVE");
                    appointmentRepository.persist(entity);
                    return entity;
                })
                .map(appointmentMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("La cita no debe ser null al crearlo."));
    }

    @Override
    @Transactional
    public AppointmentResponse update(UUID id, AppointmentRequest request) {
        return Optional.ofNullable(appointmentRepository.findById(id))
                .map(entity -> {
                    appointmentMapper.updateEntityFromRequest(request, entity);
                    // Fecha de modificacion
                    appointmentRepository.persist(entity);
                    return entity;
                })
                .map(appointmentMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public void cancel(UUID id) {
        Optional.ofNullable(appointmentRepository.findById(id))
                .ifPresent(entity -> {
                    entity.setStatus("CANCELLED");
                    appointmentRepository.persist(entity);
                });
    }
}
