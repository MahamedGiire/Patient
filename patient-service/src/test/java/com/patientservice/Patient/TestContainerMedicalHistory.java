package com.patientservice.Patient;

import com.patientservice.Patient.model.Enums.Priority;
import com.patientservice.Patient.model.MedicalHistory;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.*;
import com.patientservice.Patient.repository.MedicalHistoryRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Testcontainers
@SpringBootTest
public class TestContainerMedicalHistory {

    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    PatientRepository patientRepository;

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
        medicalHistoryRepository.deleteAll();
    }

    @Test
    public void testAddMedicalHistoryToPatient() {

        UUID uuid = UUID.fromString("76204270-f833-11ed-b67e-0242ac120002");

        Patient patient  = Patient.builder()
                .name(Name.builder().firstName("Blond").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                .build();

        patientRepository.save(patient);

        MedicalHistory medicalHistory = MedicalHistory.builder()
                .recordsID(uuid)
                .patient(patient)
                .description(Description.builder().description("You have a severe cut in your lung").build())
                .diagnosis(Diagnosis.builder().code("HHH-111").name("Lung Cancer").build())
                .treatments(Treatments.builder().treatments((Arrays.asList("Niet roken", "Meer rennen"))).build())
                .medications(Medications.builder().medications((Arrays.asList("Ibufrofen", "Paracetamol"))).build())
                .priority(Priority.MEDIUM)
                .build();

        medicalHistoryRepository.save(medicalHistory);

        Optional<MedicalHistory> getSavedMedicalHistory = medicalHistoryRepository.findByPatientAndDiagnosis(patient, Diagnosis.builder().code("HHH-111").name("Lung Cancer").build());
        List<Patient> getSavedPatientMedicalHistory = medicalHistoryRepository.findByPatientID(patient.getId());

        Assertions.assertEquals(false, getSavedPatientMedicalHistory.isEmpty());
        Assertions.assertEquals(medicalHistory.getDescription().getDescription(), getSavedMedicalHistory.get().getDescription().getDescription());
        Assertions.assertEquals(medicalHistory.getPatient().getName().getFirstName(), getSavedMedicalHistory.get().getPatient().getName().getFirstName());
        Assertions.assertEquals(medicalHistory.getPatient().getName().getLastName(), getSavedMedicalHistory.get().getPatient().getName().getLastName());
    }

}
