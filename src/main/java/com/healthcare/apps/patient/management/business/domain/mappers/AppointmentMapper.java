package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.AppointmentEntity;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "jakarta")
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);

    @Mapping(target = "id", ignore = true)
    AppointmentEntity toEntity(AppointmentRequest appointmentRequest);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(AppointmentRequest request, @MappingTarget AppointmentEntity entity);
}
