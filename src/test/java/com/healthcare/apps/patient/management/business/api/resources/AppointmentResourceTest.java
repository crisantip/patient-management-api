package com.healthcare.apps.patient.management.business.api.resources;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.healthcare.apps.patient.management.business.domain.services.AppointmentService;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import com.healthcare.apps.patient.management.model.CalendarizationResponse;
import com.healthcare.apps.patient.management.model.DoctorResponse;
import com.healthcare.apps.patient.management.model.BranchResponse;
import com.healthcare.apps.patient.management.model.SpecialtyResponse;
import com.healthcare.apps.patient.management.model.PatientResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
class AppointmentResourceTest {

    @InjectMock
    AppointmentService appointmentService;

    @Disabled
    @Test
    void getAllAppointmentsOk200() {
        var specialty = new SpecialtyResponse()
                .id(1L)
                .name("Cardiología")
                .active(true);
        
        var doctor = new DoctorResponse()
                .id(1L)
                .name("Dr. Edgar Vargas")
                .cedula("1001234567")
                .specialty(specialty)
                .active(true);
        
        var branch = new BranchResponse()
                .id(1L)
                .name("Cumbayá")
                .address("Av. Principal 123")
                .active(true);
        
        var calendarization = new CalendarizationResponse()
                .id(1L)
                .doctor(doctor)
                .branch(branch)
                .schedule(OffsetDateTime.now())
                .active(true);
        
        var patient = new PatientResponse()
                .id("1234546789")
                .name("Juan Pérez")
                .email("juan@email.com")
                .phone("0987654321");
        
        var appointment = new AppointmentResponse()
                .id(UUID.randomUUID().toString())
                .patient(patient)
                .calendarization(calendarization)
                .creationDate(OffsetDateTime.now())
                .status(AppointmentResponse.StatusEnum.ACTIVE);
        
        Mockito.when(appointmentService.getAll())
                .thenReturn(List.of(appointment));
        
        RestAssured
                .given()
                .when()
                .get("/appointments")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(1))
                .body("[0].patient.id", Matchers.equalTo("1234546789"));
    }
}