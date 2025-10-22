package com.healthcare.apps.patient.management.business.commons;

import java.util.Optional;
import java.util.UUID;

public class ResourceUtil {

    public static UUID toUuid(String str) {
        return Optional.ofNullable(str)
                .map(s -> {
                    try {
                        return UUID.fromString(str);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                })
                .orElse(null);
    }
}
