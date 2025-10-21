package com.healthcare.apps.patient.management.business.api.resources;

import java.util.List;

import com.healthcare.apps.patient.management.api.PatientsApi;
import com.healthcare.apps.patient.management.model.PatientResponse;

import jakarta.ws.rs.core.Response;

public class PatientResource implements PatientsApi {

    @Override
    public Response getPatientById(String id) {

        List<PatientResponse> patients = List.of(
                new PatientResponse().id("123").name("John").email("k@d.com").phone("54545"),
                new PatientResponse().id("456").name("Path").email("e@j.com").phone("45685")
        );
        return Response.ok(patients).build();
    }
}
