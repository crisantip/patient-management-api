package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.PatientEntity;
import com.healthcare.apps.patient.management.model.PatientRequest;
import com.healthcare.apps.patient.management.model.PatientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface PatientMapper {

    PatientResponse toResponse(PatientEntity patientEntity);

    PatientEntity toEntity(PatientRequest patientRequest);
}