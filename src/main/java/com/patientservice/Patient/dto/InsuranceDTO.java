package com.patientservice.Patient.dto;

import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InsuranceDTO {
    private Long id;
    private Patient patient;
    private Policy policy;
    private Provider provider;
    private Money money;
    private Coverage coverage;
    private LocalDate startDate;
    private LocalDate endDate;
}
