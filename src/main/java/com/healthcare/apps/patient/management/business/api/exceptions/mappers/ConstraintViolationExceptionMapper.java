package com.healthcare.apps.patient.management.business.api.exceptions.mappers;

import com.healthcare.apps.patient.management.business.api.exceptions.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

// Representa al ControllerAdvicer en spring que sirve para errores globales
// Ej. 400 campos con valores incorrectos
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var violations = exception.getConstraintViolations()
                .stream()
                .map(v -> new ErrorResponse.Violation(
                        extractFieldName(v.getPropertyPath().toString()),
                        v.getMessage()
                ))
                .toList();
        var error =  new ErrorResponse(
                "Constraint violation",
                BAD_REQUEST.getStatusCode(),
                "Se encontraron errores de validacion",
                violations
        );
        return Response.status(BAD_REQUEST).entity(error).build();
    }

    private String extractFieldName(String propertyPath) {
        // Obtiene el último segmento después del punto "."
        int lastDot = propertyPath.lastIndexOf('.');
        return lastDot == -1 ? propertyPath : propertyPath.substring(lastDot + 1);
    }
}
