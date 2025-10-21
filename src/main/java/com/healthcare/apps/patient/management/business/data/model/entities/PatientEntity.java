package com.healthcare.apps.patient.management.business.data.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_patient")
public class PatientEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
}
