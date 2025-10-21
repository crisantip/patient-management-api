package com.healthcare.apps.patient.management.business.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;

public interface AppointmentService {

    List<AppointmentResponse> getAll();

    Optional<AppointmentResponse> getById(UUID id);

    AppointmentResponse create(AppointmentRequest request);

    AppointmentResponse update(UUID id, AppointmentRequest request);

    void delete(UUID id);
}
