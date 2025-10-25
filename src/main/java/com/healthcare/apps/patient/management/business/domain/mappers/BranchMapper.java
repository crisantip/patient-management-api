package com.healthcare.apps.patient.management.business.domain.mappers;

import com.healthcare.apps.patient.management.business.data.model.entities.BranchEntity;
import com.healthcare.apps.patient.management.model.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {

    BranchResponse toResponse(BranchEntity entity);
}