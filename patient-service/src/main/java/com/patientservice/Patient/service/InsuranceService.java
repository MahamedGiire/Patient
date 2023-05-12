package com.patientservice.Patient.service;

import com.patientservice.Patient.Design.Factory;
import com.patientservice.Patient.Exceptions.DatabaseOperationException;
import com.patientservice.Patient.Exceptions.DuplicateEntryException;
import com.patientservice.Patient.dto.InsuranceDTO;
import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.repository.InsuranceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    @Autowired
    private final InsuranceRepository insuranceRepository;

    @Autowired
    private final Factory factory;

    /**
     *
     * Add insurance to a patient
     *
     * @param insuranceDTO pass the dto of insurance entity
     *
     */
    @Transactional
    public void addInsurance(InsuranceDTO insuranceDTO) throws DuplicateEntryException, DatabaseOperationException {

        try {
            Insurance insurance = factory.createInsurance(insuranceDTO);
            insuranceRepository.save(insurance);
        }  catch (DuplicateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseOperationException("Error creating patient in database", e);
        }

    }

    /**
     *
     * Get insurance all insurances from the database
     *
     * @return List of insurances
     *
     */
    public List<InsuranceDTO> getAllInsurances() {
        List<Insurance> insurances = insuranceRepository.findAll();
        return insurances.stream().map(this::mapToInsuranceDTO).toList();
    }

    /**
     *
     * Map the DTO of the Insurance Entity.
     *
     * @param insurance Pass the entity to the method.
     *
     * @return Insurance DTO build with the Insurance Data
     */
    private InsuranceDTO mapToInsuranceDTO(Insurance insurance) {
        return InsuranceDTO.builder()
                .id(insurance.getId())
                .policy(insurance.getPolicy())
                .provider(insurance.getProvider())
                .patient(insurance.getPatient())
                .coverage(insurance.getCoverage())
                .money(insurance.getPremium())
                .startDate(insurance.getStartDate())
                .endDate(insurance.getEndDate())
                .build();
    }
}
