package com.healthcare.apps.patient.management.business.domain.mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.healthcare.apps.patient.management.business.data.model.entities.CalendarizationEntity;
import com.healthcare.apps.patient.management.model.CalendarizationRequest;
import com.healthcare.apps.patient.management.model.CalendarizationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CalendarizationMapper {

    CalendarizationResponse toResponse(CalendarizationEntity entity);

    CalendarizationEntity toEntity(CalendarizationRequest request);

    void updateEntityFromRequest(CalendarizationRequest request, @MappingTarget CalendarizationEntity entity);

    default OffsetDateTime map(LocalDateTime value) {
        return value != null ? value.atOffset(ZoneOffset.UTC) : null;
    }

    default LocalDateTime map(OffsetDateTime value) {
        return value != null ? value.toLocalDateTime() : null;
    }
}