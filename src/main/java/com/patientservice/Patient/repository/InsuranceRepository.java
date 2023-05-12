package com.patientservice.Patient.repository;

import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.model.ValueObject.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface InsuranceRepository  extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByPolicy(Policy policy);
}
