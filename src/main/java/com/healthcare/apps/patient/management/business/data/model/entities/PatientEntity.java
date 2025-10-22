package com.healthcare.apps.patient.management.business.data.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paciente")
public class PatientEntity {

    @Id
    @Column(name = "cedula")
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "correo")
    private String email;
    @Column(name = "telefono")
    private String phone;
}
