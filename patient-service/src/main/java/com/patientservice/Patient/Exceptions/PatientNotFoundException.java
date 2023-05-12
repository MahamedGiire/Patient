package com.patientservice.Patient.Exceptions;

import com.patientservice.Patient.model.Patient;

/**
 * Custom exception: PatientNotFoundException
 */
public class PatientNotFoundException  extends RuntimeException {
    public PatientNotFoundException(Patient patientId) {
        super(String.format("Patient with ID %s not found.", patientId.toString()));
    }

}
