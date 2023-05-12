package com.patientservice.Patient.controller;

import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.PatientNotFoundException;
import com.patientservice.Patient.dto.MedicalHistoryDTO;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicalHistory")
@RequiredArgsConstructor
public class MedicalHistoryController {

    @Autowired
    private final MedicalHistoryService medicalHistoryService;

    /**
     * Add a medical history to a patient
     *
     * @param medicalHistoryDTO, dto of the medical history entity
     *
     * @return Response Entity, with the dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<MedicalHistory> addMedicalRecord(@RequestBody MedicalHistoryDTO medicalHistoryDTO){
        try {
            this.medicalHistoryService.addMedicalRecord(medicalHistoryDTO);
            return new ResponseEntity(medicalHistoryDTO, HttpStatus.CREATED);
        } catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all medical history's in the database
     *
     * @return List, of medical history's
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalHistoryDTO> getAllMedicalHistory(){
        return medicalHistoryService.getAllMedicalHistory();
    }

    /**
     * Get the patient of a certain medical history
     *
     * @param id of the patient
     *
     * @return patient
     */
    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getMedicalHistoryByPatient(@PathVariable Long id) {
        try {
            List<Patient> patientList = medicalHistoryService.getPatientInMedicalHistory(id);

            for (Patient patient : patientList) {
                if (id.equals(patient.getId())){
                    return ResponseEntity.ok(patient);
                }
            }

            return ResponseEntity.ok("Empty");

        } catch (PatientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DatabaseOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error finding patient in database.");
        }
    }
}
