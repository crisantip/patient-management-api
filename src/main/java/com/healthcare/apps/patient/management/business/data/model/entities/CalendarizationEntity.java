package com.healthcare.apps.patient.management.business.data.model.entities;

import java.time.LocalDateTime;

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
@Table(name = "tb_calendarizacion")
public class CalendarizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "id_sucursal")
    private BranchEntity branch;

    @Column(name = "horario")
    private LocalDateTime schedule;

    @Column(name = "activo")
    private Boolean active = true;
}