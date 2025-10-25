package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.SpecialtyEntity;
import com.healthcare.apps.patient.management.model.SpecialtyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecialtyMapper {

    SpecialtyResponse toResponse(SpecialtyEntity entity);
}