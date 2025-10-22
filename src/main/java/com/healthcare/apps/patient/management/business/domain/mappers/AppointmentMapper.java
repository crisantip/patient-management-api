package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.AppointmentEntity;
import com.healthcare.apps.patient.management.model.AppointmentRequest;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface AppointmentMapper {

    AppointmentResponse toResponse(AppointmentEntity appointmentEntity);

    AppointmentEntity toEntity(AppointmentRequest appointmentRequest);
}
