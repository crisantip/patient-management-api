package com.healthcare.apps.patient.management.business.api.exceptions;

import java.util.List;

public record ErrorResponse(
        String title,
        int status,
        String detail,
        List<Violation> violations
) {
    public record Violation(String field, String message) {}
}
