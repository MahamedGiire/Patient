package com.patientservice.Patient.repository;

import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.model.ValueObject.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface InsuranceRepository  extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByPolicy(Policy policy);
}
