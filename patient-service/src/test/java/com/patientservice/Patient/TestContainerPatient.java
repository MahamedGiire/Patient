package com.patientservice.Patient;

import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.DateOfBirth;
import com.patientservice.Patient.model.ValueObject.Email;
import com.patientservice.Patient.model.ValueObject.Name;
import com.patientservice.Patient.model.ValueObject.PhoneNumber;
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


@Testcontainers
@SpringBootTest
public class TestContainerPatient {

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
    }

    @Test
    void testCreatePatient(){
        Patient patient  = Patient.builder()
                .name(Name.builder().firstName("Blond").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                .build();

        patientRepository.save(patient);

        Optional<Patient> getSavedPatient = patientRepository.findById(patient.getId());

        Assertions.assertEquals(false, getSavedPatient.isEmpty());
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(
                Patient.builder()
                        .name(Name.builder().firstName("John").lastName("Doe").build())
                        .email(Email.builder().address("john.doe@example.com").build())
                        .mobile(PhoneNumber.builder().number("0612345678").build())
                        .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                        .build(),

                Patient.builder()
                        .name(Name.builder().firstName("Ham").lastName("Sam").build())
                        .email(Email.builder().address("john.doe@example.com").build())
                        .mobile(PhoneNumber.builder().number("0612345678").build())
                        .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                        .build(),

                Patient.builder()
                        .name(Name.builder().firstName("Jeffeth").lastName("Sam").build())
                        .email(Email.builder().address("john.doe@example.com").build())
                        .mobile(PhoneNumber.builder().number("0612345678").build())
                        .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                        .build()

        );
        patientRepository.saveAll(patients);

        List<Patient> getSavedPatient = patientRepository.findAll();

        Assertions.assertEquals(3, getSavedPatient.size());
        Assertions.assertEquals(false, getSavedPatient.isEmpty());

    }

    @Test
    public void testGetPatientById() {
        Patient patient = Patient.builder()
                .name(Name.builder().firstName("Hammer").lastName("Doe").build())
                .email(Email.builder().address("john.doe@example.com").build())
                .mobile(PhoneNumber.builder().number("0612345678").build())
                .dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
                .build();

        patientRepository.save(patient);

        Optional<Patient> getSavedPatient = patientRepository.findById(patient.getId());

        Assertions.assertEquals(patient.getName().getFirstName(), getSavedPatient.get().getName().getFirstName());
        Assertions.assertEquals(patient.getName().getLastName(), getSavedPatient.get().getName().getLastName());
        Assertions.assertEquals(patient.getDateOfBirth().toString(), getSavedPatient.get().getDateOfBirth().toString());
        Assertions.assertEquals(patient.getEmail().getAddress(), getSavedPatient.get().getEmail().getAddress());
    }


}
