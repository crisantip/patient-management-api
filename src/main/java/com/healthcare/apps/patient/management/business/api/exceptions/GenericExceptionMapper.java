package com.healthcare.apps.patient.management.business.api.exceptions;

import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

// Representa al ControllerAdvicer en spring que sirve para errores globales
// Ej. 500 error interno
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        var error = new ErrorResponse(
                "Error interno",
                INTERNAL_SERVER_ERROR.getStatusCode(),
                exception.getMessage(),
                List.of()
        );
        return Response.status(INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
