package com.healthcare.apps.patient.management.business.data.repositories;

import com.healthcare.apps.patient.management.business.data.model.entities.PatientEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepositoryBase<PatientEntity, String> {
}
