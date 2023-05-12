package com.patientservice.Patient.Design;

import com.patientservice.Patient.Exceptions.DuplicateEntryException;
import com.patientservice.Patient.Exceptions.PatientNotFoundException;
import com.patientservice.Patient.dto.InsuranceDTO;
import com.patientservice.Patient.dto.MedicalHistoryDTO;
import com.patientservice.Patient.dto.PatientDTO;
import com.patientservice.Patient.model.Generator.GenerateRandomUUID;
import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.repository.InsuranceRepository;
import com.patientservice.Patient.repository.MedicalHistoryRepository;
import com.patientservice.Patient.repository.PatientRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Builder
@Slf4j

@Component
public class Factory {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private final InsuranceRepository insuranceRepository;

    /**
     * Creates a new instance of the Patient class.
     *
     * @param patientDTO pass the DTO of the patient entity
     *
     * Check if name already exist.
     * Check if email already exist.
     *
     * @return the patient
     */
    public Patient createPatient(PatientDTO patientDTO) {
        if (patientRepository.findByName(patientDTO.getName()).isPresent()) {
            throw new DuplicateEntryException("Name already exists.");
        }
        if (patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email already exists.");
        }

        Patient patient = new Patient(patientDTO.getId(), patientDTO.getName(), patientDTO.getEmail(), patientDTO.getMobile(), patientDTO.getDateOfBirth());

        return patient;
    }

    /**
     * Creates a new instance of the Medical History class.
     *
     * @param medicalHistoryDTO pass the DTO of the Medical History entity
     *
     * Check if patient already exist.
     * Check if patient has already received the diagnosis.
     *
     * @return the Medical History
     */
    public MedicalHistory createMedicalHistory(MedicalHistoryDTO medicalHistoryDTO) {
        if (patientRepository.findById(medicalHistoryDTO.getPatient().getId()).isEmpty()) {
            throw new PatientNotFoundException(medicalHistoryDTO.getPatient());
        }
        if (medicalHistoryRepository.findByPatientAndDiagnosis(medicalHistoryDTO.getPatient(), medicalHistoryDTO.getDiagnosis()).isPresent()) {
            throw new DuplicateEntryException("Diagnosis already exists for the patient.");
        }

        MedicalHistory medicalHistory = new MedicalHistory(GenerateRandomUUID.generate(),
                                            medicalHistoryDTO.getPatient(),
                                            medicalHistoryDTO.getDescription(),
                                            medicalHistoryDTO.getDiagnosis(),
                                            medicalHistoryDTO.getTreatments(),
                                            medicalHistoryDTO.getMedications(),
                                            medicalHistoryDTO.getPriority());

        return medicalHistory;
    }

    /**
     * Creates a new instance of the Insurance class.
     *
     * @param insuranceDTO pass the DTO of the Insurance entity
     *
     * Check if patient already exist.
     * Check if the policy number already exist.
     *
     * @return the insurance
     */
    public Insurance createInsurance(InsuranceDTO insuranceDTO){
        if (patientRepository.findById(insuranceDTO.getPatient().getId()).isEmpty()) {
            throw new PatientNotFoundException(insuranceDTO.getPatient());
        }
        if (insuranceRepository.findByPolicy(insuranceDTO.getPolicy()).isPresent()) {
            throw new DuplicateEntryException("Policy already exists.");
        }

        Insurance insurance = new Insurance(insuranceDTO.getId(),
                                            insuranceDTO.getPatient(),
                                            insuranceDTO.getPolicy(),
                                            insuranceDTO.getProvider(),
                                            insuranceDTO.getMoney(),
                                            insuranceDTO.getCoverage(),
                                            insuranceDTO.getStartDate(),
                                            insuranceDTO.getEndDate());
        return insurance;
    }

}
