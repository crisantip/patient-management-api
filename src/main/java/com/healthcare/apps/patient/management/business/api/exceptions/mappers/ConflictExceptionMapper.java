package com.healthcare.apps.patient.management.business.api.exceptions.mappers;

import java.util.List;

import com.healthcare.apps.patient.management.business.api.exceptions.ConflictException;
import com.healthcare.apps.patient.management.business.api.exceptions.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

    @Override
    public Response toResponse(ConflictException exception) {
        var error = new ErrorResponse(
                "Conflicto",
                CONFLICT.getStatusCode(),
                exception.getMessage(),
                List.of()
        );
        return Response.status(CONFLICT).entity(error).build();
    }
}