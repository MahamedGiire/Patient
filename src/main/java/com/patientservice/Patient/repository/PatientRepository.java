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
 * Beste Tom,
 *
 * Ik heb ervoor gekozen om 3 interfaces te maken omdat de complexiteit van het systeem op kan lopen, en ik daardoor liever
 * geen volle interface heb met alle Query's die worden geroepen naar andere Entiteiten.
 *
 * Ik heb hier ook een beetje onderzoek naar gedaan en het is toegestaan volgens DDD principes om in 1 aggregate alsnog meerdere
 * repositories aan te houden als het wenselijker is dan een enkele repo.
 * */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByName(Name name);
    Optional<Patient> findByEmail(Email email);
    Optional<Patient> findById(Long id);

    @Query("SELECT m FROM MedicalHistory m, Patient p WHERE m.patient = p")
    List<MedicalHistory> getMedicalHistoryByPatient(Long id);
}
