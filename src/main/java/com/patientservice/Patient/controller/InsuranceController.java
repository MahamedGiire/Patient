package com.patientservice.Patient.controller;

import com.patientservice.Patient.dto.InsuranceDTO;
import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Insurance> createPatient(@RequestBody InsuranceDTO insuranceDTO){
        try {
            this.insuranceService.addInsurance(insuranceDTO);
            return new ResponseEntity(insuranceDTO, HttpStatus.CREATED);
        } catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceDTO> getAllInsurances(){
        return insuranceService.getAllInsurances();
    }
}
