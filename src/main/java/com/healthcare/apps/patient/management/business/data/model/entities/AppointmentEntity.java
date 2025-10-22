package com.healthcare.apps.patient.management.business.data.model.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cita")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cita")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cedula_paciente")
    private PatientEntity patient;

    @Column(name = "sucursal")
    private String branch;

    @Column(name = "especialidad")
    private String specialty;

    @Column(name = "medico")
    private String doctor;

    @Column(name = "fecha")
    private LocalDate date;

    @Column(name = "hora")
    private String time;
    
    @Column(name = "estado")
    private String status = "ACTIVE";
}
