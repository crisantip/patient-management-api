package com.healthcare.apps.patient.management.business.data.model.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cita")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cita")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cedula_paciente")
    private PatientEntity patient;

    @OneToOne
    @JoinColumn(name = "id_calendarizacion")
    private CalendarizationEntity calendarization;

    @Column(name = "fecha_creacion")
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    private LocalDateTime modificationDate;
    
    @Column(name = "estado")
    private String status = "ACTIVE";
}
