package com.healthcare.apps.patient.management.business.domain.services.impl;

import java.util.List;
import java.util.Optional;

import com.healthcare.apps.patient.management.business.api.exceptions.NotFoundException;
import com.healthcare.apps.patient.management.business.data.repositories.BranchRepository;
import com.healthcare.apps.patient.management.business.data.repositories.CalendarizationRepository;
import com.healthcare.apps.patient.management.business.data.repositories.DoctorRepository;
import com.healthcare.apps.patient.management.business.domain.mappers.CalendarizationMapper;
import com.healthcare.apps.patient.management.business.domain.services.CalendarizationService;
import com.healthcare.apps.patient.management.model.CalendarizationRequest;
import com.healthcare.apps.patient.management.model.CalendarizationResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CalendarizationServiceImpl implements CalendarizationService {

    @Inject
    CalendarizationRepository calendarizationRepository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    BranchRepository branchRepository;

    @Inject
    CalendarizationMapper calendarizationMapper;

    @Override
    public List<CalendarizationResponse> getAll() {
        return calendarizationRepository
                .find("active", true)
                .stream()
                .map(calendarizationMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<CalendarizationResponse> getById(Long id) {
        return Optional.ofNullable(calendarizationRepository.findById(id))
                .map(calendarizationMapper::toResponse);
    }

    @Override
    @Transactional
    public CalendarizationResponse create(CalendarizationRequest request) {
        var doctor = Optional.ofNullable(doctorRepository.findById(request.getDoctorId()))
                .orElseThrow(() -> new NotFoundException("Médico no encontrado: " + request.getDoctorId()));

        var branch = Optional.ofNullable(branchRepository.findById(request.getBranchId()))
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada: " + request.getBranchId()));

        return Optional.ofNullable(request)
                .map(calendarizationMapper::toEntity)
                .map(entity -> {
                    entity.setDoctor(doctor);
                    entity.setBranch(branch);
                    entity.setActive(true);
                    calendarizationRepository.persist(entity);
                    return entity;
                })
                .map(calendarizationMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("La calendarización no debe ser null"));
    }

    @Override
    @Transactional
    public CalendarizationResponse update(Long id, CalendarizationRequest request) {
        var doctor = Optional.ofNullable(doctorRepository.findById(request.getDoctorId()))
                .orElseThrow(() -> new NotFoundException("Médico no encontrado: " + request.getDoctorId()));

        var branch = Optional.ofNullable(branchRepository.findById(request.getBranchId()))
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada: " + request.getBranchId()));

        return Optional.ofNullable(calendarizationRepository.findById(id))
                .map(entity -> {
                    calendarizationMapper.updateEntityFromRequest(request, entity);
                    entity.setDoctor(doctor);
                    entity.setBranch(branch);
                    calendarizationRepository.persist(entity);
                    return entity;
                })
                .map(calendarizationMapper::toResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional.ofNullable(calendarizationRepository.findById(id))
                .ifPresent(entity -> {
                    entity.setActive(false);
                    calendarizationRepository.persist(entity);
                });
    }
}