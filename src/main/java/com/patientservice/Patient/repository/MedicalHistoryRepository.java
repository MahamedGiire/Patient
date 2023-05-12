package com.patientservice.Patient.repository;

import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, UUID> {
    Optional<MedicalHistory> findByPatientAndDiagnosis(Patient patient, Diagnosis diagnosis);


    @Query("SELECT p FROM MedicalHistory m, Patient p WHERE m.patient = p")
    List<Patient> findByPatientID(Long id);

}
