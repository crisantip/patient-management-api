package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.DoctorEntity;
import com.healthcare.apps.patient.management.model.DoctorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DoctorMapper {

    DoctorResponse toResponse(DoctorEntity entity);
}