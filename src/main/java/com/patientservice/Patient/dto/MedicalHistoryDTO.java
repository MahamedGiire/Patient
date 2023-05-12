package com.patientservice.Patient.dto;

import com.patientservice.Patient.model.Enums.Priority;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.Description;
import com.patientservice.Patient.model.ValueObject.Diagnosis;
import com.patientservice.Patient.model.ValueObject.Medications;
import com.patientservice.Patient.model.ValueObject.Treatments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicalHistoryDTO {
    private UUID recordsID;
    private Patient patient;
    private Description description;
    private Diagnosis diagnosis;
    private Treatments treatments;
    private Medications medications;
    private Priority priority;
}
