package com.patientservice.Patient.repository;

import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Beste Tom,
 *
 * Ik heb ervoor gekozen om 3 interfaces te maken omdat de complexiteit van het systeem op kan lopen, en ik daardoor liever
 * geen volle interface heb met alle Query's die worden geroepen naar andere Entiteiten.
 *
 * Ik heb hier ook een beetje onderzoek naar gedaan en het is toegestaan volgens DDD principes om in 1 aggregate alsnog meerdere
 * repositories aan te houden als het wenselijker is dan een enkele repo.
 * */
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, UUID> {
    Optional<MedicalHistory> findByPatientAndDiagnosis(Patient patient, Diagnosis diagnosis);


    @Query("SELECT p FROM MedicalHistory m, Patient p WHERE m.patient = p")
    List<Patient> findByPatientID(Long id);

}
