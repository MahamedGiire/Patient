package com.patientservice.Patient.dto.AppointmentDTO;

import com.patientservice.Patient.model.ValueObject.Category;
import com.patientservice.Patient.model.ValueObject.Doctor;
import com.patientservice.Patient.model.ValueObject.Hospital;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * DTO for the Appointment Entity
 */
public class AppointmentDTO {
    @NotNull(message = "Id must not be null")
    private Long id;
    @NotNull(message = "Id must not be null")
    private Long patientId;
    private LocalDate appointmentDate;
    private Doctor doctor;
    private Hospital hospital;
    private Category category;
}
