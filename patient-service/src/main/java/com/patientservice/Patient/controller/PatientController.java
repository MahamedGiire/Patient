package com.patientservice.Patient.controller;

import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.PatientNotFoundException;
import com.patientservice.Patient.dto.PatientDTO;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO){
        try {
            this.patientService.createPatient(patientDTO);
            return new ResponseEntity(patientDTO, HttpStatus.CREATED);
        } catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientDTO> getAllPatienten(){
        return patientService.getAllPatienten();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        try {
            Optional<Patient> patientDTO = patientService.findById(id);
            return ResponseEntity.ok(patientDTO);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DatabaseOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error finding patient in database.");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient with ID " + id + " deleted successfully.");
        } catch (PatientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DatabaseOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting patient from database.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        try {
            patientService.updatePatient(id, patientDTO);
            return ResponseEntity.ok("Patient with ID " + id + " updated successfully.");
        } catch (PatientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DatabaseOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating patient in database.");
        }
    }

    @GetMapping("/medicalHistory/{id}")
    public ResponseEntity<?> getMedicalHistoryByPatient(@PathVariable Long id) {
        try {
            List<MedicalHistory> medicalHistory = patientService.getMedicalHistoryByPatient(id);
            return ResponseEntity.ok(medicalHistory);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DatabaseOperationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error finding patient in database.");
        }
    }


}
