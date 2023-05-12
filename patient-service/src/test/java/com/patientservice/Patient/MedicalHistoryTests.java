package com.patientservice.Patient;

import com.patientservice.Patient.dto.MedicalHistoryDTO;
import com.patientservice.Patient.model.Generator.GenerateRandomUUID;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.*;
import com.patientservice.Patient.repository.MedicalHistoryRepository;
import com.patientservice.Patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicalHistoryTests {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        medicalHistoryRepository.deleteAll();
    }

    @Test
    public void testAddMedicalHistoryToPatient() {

        Patient patient = Patient.builder()
                .name(Name.builder().firstName("John").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1990).build())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        MedicalHistoryDTO medicalHistoryDTO = MedicalHistoryDTO.builder()
                .recordsID(GenerateRandomUUID.generate())
                .patient(savedPatient)
                .description(Description.builder().description("You have a severe cut in your lung").build())
                .diagnosis(Diagnosis.builder().code("HHH-111").name("Lung Cancer").build())
                .treatments(Treatments.builder().treatments((Arrays.asList("Niet roken", "Meer rennen"))).build())
                .medications(Medications.builder().medications((Arrays.asList("Ibufrofen", "Paracetamol"))).build())
                .build();

        ResponseEntity<MedicalHistoryDTO> responseEntity = restTemplate.postForEntity("/api/medicalHistory", medicalHistoryDTO, MedicalHistoryDTO.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        MedicalHistoryDTO savedMedicalHistoryDTO = responseEntity.getBody();
        assertNotNull(savedMedicalHistoryDTO);
        assertNotNull(savedMedicalHistoryDTO.getPatient().getId());
        assertEquals(medicalHistoryDTO.getDescription(), savedMedicalHistoryDTO.getDescription());
        assertEquals(medicalHistoryDTO.getDiagnosis(), savedMedicalHistoryDTO.getDiagnosis());
        assertEquals(savedPatient.getId(), savedMedicalHistoryDTO.getPatient().getId());
    }
}
