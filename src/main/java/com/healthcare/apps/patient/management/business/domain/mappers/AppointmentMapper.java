package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.AppointmentEntity;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);

    AppointmentEntity toEntity(AppointmentRequest appointmentRequest);

    void updateEntityFromRequest(AppointmentRequest request, @MappingTarget AppointmentEntity entity);
}
