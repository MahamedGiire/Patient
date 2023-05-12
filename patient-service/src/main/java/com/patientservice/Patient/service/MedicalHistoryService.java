package com.patientservice.Patient.service;

import com.patientservice.Patient.Design.Factory;
import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.DuplicateEntryException;
import com.patientservice.Patient.dto.MedicalHistoryDTO;
import com.patientservice.Patient.model.Enums.Priority;
import com.patientservice.Patient.model.Generator.GenerateRandomUUID;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.repository.MedicalHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {
    @Autowired
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private final Factory factory;

    /**
     * NON-Trivial User Story:
     * As a Actor,
     * I would like a priority on a Patient's Medical History,
     * So that i can determine the situation faster.
     *
     * Add a Medical Record to a Patient
     *
     * @param medicalHistoryDTO Pass the MedicalHistoryDTO to addMedicalRecord
     */
    @Transactional
    public void addMedicalRecord(MedicalHistoryDTO medicalHistoryDTO) throws DuplicateEntryException, DatabaseOperationException {

        try {
            MedicalHistory medicalHistory = factory.createMedicalHistory(medicalHistoryDTO);

            int treatmentSize = medicalHistory.getTreatments().getTreatments().size();
            int medicationSize = medicalHistory.getMedications().getMedications().size();

            if (treatmentSize >= 5 || medicationSize >= 5) {
                medicalHistory.setPriority(Priority.HIGH);
            } else if (treatmentSize >= 1 || medicationSize >= 1) {
                medicalHistory.setPriority(Priority.MEDIUM);
            } else {
                medicalHistory.setPriority(Priority.LOW);
            }
            medicalHistoryDTO.setRecordsID(GenerateRandomUUID.generate());
            medicalHistoryRepository.save(medicalHistory);

        }  catch (DuplicateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseOperationException(e.getMessage());
        }

    }

    public List<MedicalHistoryDTO> getAllMedicalHistory() {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findAll();
        return medicalHistories.stream().map(this::mapToMedicalHistoryDTO).toList();
    }

    private MedicalHistoryDTO mapToMedicalHistoryDTO(MedicalHistory medicalHistory) {
        return MedicalHistoryDTO.builder()
                .recordsID(medicalHistory.getRecordsID())
                .patient(medicalHistory.getPatient())
                .description(medicalHistory.getDescription())
                .diagnosis(medicalHistory.getDiagnosis())
                .treatments(medicalHistory.getTreatments())
                .medications(medicalHistory.getMedications())
                .build();
    }

    public List<Patient> getPatientInMedicalHistory(Long patientID) {
        List<Patient> patient = medicalHistoryRepository.findByPatientID(patientID);
        return patient;
    }

}
