package com.patientservice.Patient.service;

import com.patientservice.Patient.Design.Factory;
import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.DuplicateEntryException;
import com.patientservice.Patient.Exceptions.PatientNotFoundException;
import com.patientservice.Patient.dto.PatientDTO;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final Factory factory;

    @Transactional
    public void createPatient(PatientDTO patientDTO) throws DuplicateEntryException, DatabaseOperationException {

        try {
            Patient patient = factory.createPatient(patientDTO);
            patientRepository.save(patient);
        }  catch (DuplicateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseOperationException("Error creating patient in database", e);
        }

    }

    public List<PatientDTO> getAllPatienten() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::mapToPatientDTO).toList();
    }

    private PatientDTO mapToPatientDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .dateOfBirth(patient.getDateOfBirth())
                .email(patient.getEmail())
                .mobile(patient.getMobile())
                    .build();
    }

    public void deletePatient(Long id) throws DatabaseOperationException {
        try {
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Patient not found"));

            patientRepository.delete(patient);
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseOperationException("Error deleting patient from database", e);
        }
    }

    public void updatePatient(Long id, PatientDTO patientDTO) throws DatabaseOperationException {
        try {
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new Exception("Patient cannot be found"));

            patient.setName(patientDTO.getName());
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
            patient.setEmail(patientDTO.getEmail());
            patient.setMobile(patientDTO.getMobile());

            patientRepository.save(patient);
        } catch (PatientNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseOperationException("Error updating patient in database", e);
        }
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public List<MedicalHistory> getMedicalHistoryByPatient(Long patientID) {
        List<MedicalHistory> medicalHistories = patientRepository.getMedicalHistoryByPatient(patientID);
        return medicalHistories;
    }
}