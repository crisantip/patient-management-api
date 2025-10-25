package com.healthcare.apps.patient.management.business.data.repositories;

import com.healthcare.apps.patient.management.business.data.model.entities.DoctorEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DoctorRepository implements PanacheRepository<DoctorEntity> {
}