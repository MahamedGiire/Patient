package com.patientservice.Patient;

import com.patientservice.Patient.model.Insurance;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.*;
import com.patientservice.Patient.repository.InsuranceRepository;
import com.patientservice.Patient.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Testcontainers
@SpringBootTest
public class TestContainerInsurance {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("admin")
            .withDatabaseName("patient");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @BeforeEach
    public void setup() {
        patientRepository.deleteAll();
        insuranceRepository.deleteAll();
    }

    @Test
    void testAddInsuranceToPatient(){
        Patient patient  = Patient.builder()
                .name(Name.builder().firstName("Blond").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                .build();

        patientRepository.save(patient);


        Insurance insurance = Insurance.builder()
                .patient(patient)
                .provider(Provider.builder().name("Zilveren Kruis").address("Heidelberglaan 15").build())
                .policy(Policy.builder().policyNumber("HL15-IOIO").build())
                .premium(Money.builder().currency("EURO").amount(150.0).build())
                .coverage(Coverage.builder().coverageLimit(5.0).coverageType("High Class").build())
                .startDate(LocalDate.of(2022, 12, 5))
                .endDate(LocalDate.of(2023, 12, 5))
                .build();

        insuranceRepository.save(insurance);

        Optional<Insurance> getSavedInsurance = insuranceRepository.findByPolicy(Policy.builder().policyNumber("HL15-IOIO").build());

        Assertions.assertEquals(insurance.getPatient().getName().getFirstName(), getSavedInsurance.get().getPatient().getName().getFirstName());
        Assertions.assertEquals(insurance.getPatient().getName().getLastName(), getSavedInsurance.get().getPatient().getName().getLastName());
        Assertions.assertEquals(insurance.getPolicy().getPolicyNumber(), getSavedInsurance.get().getPolicy().getPolicyNumber());
        Assertions.assertEquals(insurance.getPremium().getAmount(), getSavedInsurance.get().getPremium().getAmount());
        Assertions.assertEquals(insurance.getPremium().getCurrency(), getSavedInsurance.get().getPremium().getCurrency());



    }


}
