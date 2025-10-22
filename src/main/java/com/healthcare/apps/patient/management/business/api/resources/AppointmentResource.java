package com.healthcare.apps.patient.management.business.api.resources;

import java.util.Optional;

import com.healthcare.apps.patient.management.api.AppointmentsApi;
import com.healthcare.apps.patient.management.business.domain.services.AppointmentService;
import com.healthcare.apps.patient.management.model.AppointmentRequest;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import static com.healthcare.apps.patient.management.business.commons.ResourceUtil.toUuid;

public class AppointmentResource implements AppointmentsApi {

    @Inject
    AppointmentService appointmentService;

    @Override
    public Response createAppointment(AppointmentRequest appointmentRequest) {
        return Optional.ofNullable(appointmentService.create(appointmentRequest))
                .map(response -> {
                    var location = UriBuilder.fromPath("/appointments/{id}")
                            .build(response.getId());
                    return Response.created(location)
                            .entity(response)
                            .build();
                })
                .orElseGet(() -> Response.serverError().build());
    }

    @Override
    public Response deleteAppointment(String id) {
        return null;
    }

    @Override
    public Response getAllAppointments() {
        return Optional.ofNullable(appointmentService.getAll())
                .filter(appointments -> !appointments.isEmpty())
                .map(appointments -> Response.ok(appointments).build())
                .orElse(Response.noContent().build());
    }

    @Override
    public Response getAppointmentById(String id) {
        return Optional.ofNullable(toUuid(id))
                .flatMap(uuid -> appointmentService.getById(uuid))
                .map(appointment -> Response.ok(appointment).build())
                .orElse(Response.noContent().build());
    }

    @Override
    public Response updateAppointment(String id, AppointmentRequest appointmentRequest) {
        return null;
    }
}
