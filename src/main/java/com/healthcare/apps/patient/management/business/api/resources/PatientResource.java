package com.healthcare.apps.patient.management.business.api.resources;

import com.healthcare.apps.patient.management.api.PatientsApi;
import com.healthcare.apps.patient.management.business.domain.services.PatientService;
import com.healthcare.apps.patient.management.model.PatientRequest;
import com.healthcare.apps.patient.management.model.PatientResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class PatientResource implements PatientsApi {

    @Inject
    PatientService patientService;

    @Override
    public Response createPatient(PatientRequest patientRequest) {
        PatientResponse created = patientService.create(patientRequest);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @Override
    public Response getPatientById(String id) {
        return patientService.getById(id)
                .map(patient -> Response.ok(patient).build())
                .orElse(Response.noContent().build());
    }
}
