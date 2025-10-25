package com.healthcare.apps.patient.management.business.domain.mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.healthcare.apps.patient.management.business.data.model.entities.AppointmentEntity;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);

    AppointmentEntity toEntity(AppointmentRequest appointmentRequest);

    default OffsetDateTime map(LocalDateTime value) {
        return value != null ? value.atOffset(ZoneOffset.UTC) : null;
    }
}
