package com.patientservice.Patient.dto;

import com.patientservice.Patient.model.ValueObject.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * DTO for the Patient Entity
 */
public class PatientDTO {
    @NotNull(message = "Id must not be null")
    private Long id;
    private Name name;
    private Email email;
    private PhoneNumber mobile;
    private DateOfBirth dateOfBirth;

}
