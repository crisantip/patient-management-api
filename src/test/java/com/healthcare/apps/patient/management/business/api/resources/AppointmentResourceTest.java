package com.healthcare.apps.patient.management.business.api.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.healthcare.apps.patient.management.business.domain.services.AppointmentService;
import com.healthcare.apps.patient.management.model.AppointmentResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
class AppointmentResourceTest {

    @InjectMock
    AppointmentService appointmentService;

    @Test
    void getAllAppointmentsOk200() {
        var appointment = new AppointmentResponse()
                .id(UUID.randomUUID().toString())
                .branch("Cumbayá")
                .date(LocalDate.now())
                .doctor("Dr. Edgar Vargas")
                .time("12:00");
        Mockito.when(appointmentService.getAll())
                .thenReturn(List.of(appointment));
        RestAssured
                .given()
                .when()
                .get("/appointments")
                .then()
                .statusCode(200)
                .body("$", Matchers.hasSize(1))
                .body("[0].patientId", Matchers.equalTo("1234546789"));
    }
}