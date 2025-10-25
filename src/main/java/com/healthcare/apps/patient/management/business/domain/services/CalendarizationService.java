package com.healthcare.apps.patient.management.business.domain.services;

import java.util.List;
import java.util.Optional;

import com.healthcare.apps.patient.management.model.CalendarizationRequest;
import com.healthcare.apps.patient.management.model.CalendarizationResponse;

public interface CalendarizationService {

    List<CalendarizationResponse> getAll();

    Optional<CalendarizationResponse> getById(Long id);

    CalendarizationResponse create(CalendarizationRequest request);

    CalendarizationResponse update(Long id, CalendarizationRequest request);

    void delete(Long id);
}