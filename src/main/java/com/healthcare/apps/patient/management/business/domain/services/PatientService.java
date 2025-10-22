package com.healthcare.apps.patient.management.business.domain.services;

import java.util.Optional;

import com.healthcare.apps.patient.management.model.PatientRequest;
import com.healthcare.apps.patient.management.model.PatientResponse;

public interface PatientService {

    Optional<PatientResponse> getById(String id);
    PatientResponse create(PatientRequest request);
}
