package com.healthcare.apps.patient.management.business.data.repositories;

import java.util.UUID;

import com.healthcare.apps.patient.management.business.data.model.entities.AppointmentEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepositoryBase<AppointmentEntity, UUID> {
}
