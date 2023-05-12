package com.patientservice.Patient;

import com.patientservice.Patient.dto.InsuranceDTO;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.*;
import com.patientservice.Patient.repository.InsuranceRepository;
import com.patientservice.Patient.repository.PatientRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceTests {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        insuranceRepository.deleteAll();
    }

    @Test
    public void testAddInsuranceToPatient() {

        Patient patient = Patient.builder()
                .name(Name.builder().firstName("Blond").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        InsuranceDTO insuranceDTO = InsuranceDTO.builder()
                .patient(savedPatient)
                .provider(Provider.builder().name("Zilveren Kruis").address("Heidelberglaan 15").build())
                .policy(Policy.builder().policyNumber("HL15-IOIO").build())
                .money(Money.builder().currency("EURO").amount(150.0).build())
                .coverage(Coverage.builder().coverageLimit(5.0).coverageType("High Class").build())
                .startDate(LocalDate.of(2022, 12, 5))
                .endDate(LocalDate.of(2023, 12, 5))
                .build();


        ResponseEntity<InsuranceDTO> responseEntity = restTemplate.postForEntity("/api/insurance", insuranceDTO, InsuranceDTO.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        InsuranceDTO savedInsuranceDTO = responseEntity.getBody();
        assertNotNull(savedInsuranceDTO);
        assertNotNull(savedInsuranceDTO.getId());
        assertEquals(insuranceDTO.getProvider(), savedInsuranceDTO.getProvider());
        assertEquals(insuranceDTO.getPolicy(), savedInsuranceDTO.getPolicy());
        assertEquals(savedPatient.getId(), savedInsuranceDTO.getPatient().getId());

    }
}
