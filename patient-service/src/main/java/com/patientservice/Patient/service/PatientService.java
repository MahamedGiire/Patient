package com.patientservice.Patient.service;

import com.patientservice.Patient.Design.Factory;
import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.DuplicateEntryException;
import com.patientservice.Patient.Exceptions.PatientNotFoundException;
import com.patientservice.Patient.dto.AppointmentDTO.AppointmentDTO;
import com.patientservice.Patient.dto.PatientDTO;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.Category;
import com.patientservice.Patient.model.ValueObject.Doctor;
import com.patientservice.Patient.model.ValueObject.Hospital;
import com.patientservice.Patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final Factory factory;

    @Autowired
    private final RestTemplate restTemplate;

    /**
     *
     * Create a patient.
     *
     * @param patientDTO Pass the DTO to the method.
     *
     * Method uses the Factory class to create a new instance of patient entity.
     */
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

    /**
     *
     * Create a appointment for the patient.
     *
     */
    public void createAppointmentForPatient(Long patientId, AppointmentDTO appointmentDTO) throws DuplicateEntryException, DatabaseOperationException{

        try {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new Exception("Patient cannot be found"));
            appointmentDTO.setPatientId(patientId);

            String url = "http://appointments-app:9090/api/appointment";
            restTemplate.postForObject(url, appointmentDTO, AppointmentDTO.class);

        }  catch (DuplicateEntryException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DatabaseOperationException("Error finding patient in database", e);
        }

    }

    /**
     * Returns the patients in our DB
     *
     * @return List of patients
     */
    public List<PatientDTO> getAllPatienten() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::mapToPatientDTO).toList();
    }

    /**
     *
     * Map the DTO to the Patient Entity.
     *
     * @param patient Pass the entity to method
     *
     * @return Patient DTO build with the Patient Data
     */
    private PatientDTO mapToPatientDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .dateOfBirth(patient.getDateOfBirth())
                .email(patient.getEmail())
                .mobile(patient.getMobile())
                    .build();
    }


    /**
     *
     * Delete a patient from the database
     *
     * @param id of the patient
     *
     */
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

    /**
     *
     * Update a patient from the database
     *
     * @param id of the patient
     * @param patientDTO dto of the patient entity
     *
     */
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

    /**
     *
     * Find the patient by id
     *
     * @param id of the patient
     *
     */
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }


    /**
     *
     * Get the medical history of the patient
     *
     * @param patientID of the patient
     *
     * @return list of the medical history of patient
     *
     */
    public List<MedicalHistory> getMedicalHistoryByPatient(Long patientID) {
        List<MedicalHistory> medicalHistories = patientRepository.getMedicalHistoryByPatient(patientID);
        return medicalHistories;
    }
}
