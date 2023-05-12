package com.patientservice.Patient.repository;

import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.Email;
import com.patientservice.Patient.model.ValueObject.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * The Patient Class Repository
 *
 * Explanation:
 *
 *  Patient is the aggregate root, so there is a argument that is should all be one repository
 *  but considering: Single responsibility principle you could separate the repo's for more flexibility.
 *
 *  That's why i have chosen this option.
 *
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByName(Name name);
    Optional<Patient> findByEmail(Email email);
    Optional<Patient> findById(Long id);

    @Query("SELECT m FROM MedicalHistory m, Patient p WHERE m.patient = p")
    List<MedicalHistory> getMedicalHistoryByPatient(Long id);
}
