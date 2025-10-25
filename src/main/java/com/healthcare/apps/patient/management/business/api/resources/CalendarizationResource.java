package com.healthcare.apps.patient.management.business.api.resources;

import java.util.Optional;

import com.healthcare.apps.patient.management.api.CalendarizationsApi;
import com.healthcare.apps.patient.management.business.domain.services.CalendarizationService;
import com.healthcare.apps.patient.management.model.CalendarizationRequest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

public class CalendarizationResource implements CalendarizationsApi {

    @Inject
    CalendarizationService calendarizationService;

    @Override
    @RolesAllowed({"CALENDARIZATION_LIST"})
    public Response getAllCalendarizations() {
        return Optional.ofNullable(calendarizationService.getAll())
                .filter(calendarizations -> !calendarizations.isEmpty())
                .map(calendarizations -> Response.ok(calendarizations).build())
                .orElseGet(() -> Response.noContent().build());
    }

    @Override
    @RolesAllowed({"CALENDARIZATION_READ"})
    public Response getCalendarizationById(Long id) {
        return calendarizationService.getById(id)
                .map(calendarization -> Response.ok(calendarization).build())
                .orElseGet(() -> Response.noContent().build());
    }

    @Override
    @RolesAllowed({"CALENDARIZATION_CREATE"})
    public Response createCalendarization(CalendarizationRequest request) {
        return Optional.ofNullable(calendarizationService.create(request))
                .map(response -> {
                    var location = UriBuilder.fromPath("/calendarizations/{id}")
                            .build(response.getId());
                    return Response.created(location)
                            .entity(response)
                            .build();
                })
                .orElseGet(() -> Response.serverError().build());
    }

    @Override
    @RolesAllowed({"CALENDARIZATION_UPDATE"})
    public Response updateCalendarization(Long id, CalendarizationRequest request) {
        return Optional.ofNullable(calendarizationService.update(id, request))
                .map(response -> Response.ok(response).build())
                .orElseGet(() -> Response.noContent().build());
    }

    @Override
    @RolesAllowed({"CALENDARIZATION_DELETE"})
    public Response deleteCalendarization(Long id) {
        calendarizationService.delete(id);
        return Response.noContent().build();
    }
}