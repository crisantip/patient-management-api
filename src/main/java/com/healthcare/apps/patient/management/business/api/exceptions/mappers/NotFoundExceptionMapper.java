package com.healthcare.apps.patient.management.business.api.exceptions.mappers;

import java.util.List;

import com.healthcare.apps.patient.management.business.api.exceptions.ErrorResponse;
import com.healthcare.apps.patient.management.business.api.exceptions.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {

        var error = new ErrorResponse(
                "Recurso no encontrado",
                NOT_FOUND.getStatusCode(),
                exception.getMessage() != null ? exception.getMessage() : "El recurso solicitado no existe",
                List.of()
        );
        return Response.status(NOT_FOUND).entity(error).build();
    }
}
