package com.patientservice.Patient;

import com.patientservice.Patient.dto.PatientDTO;
import com.patientservice.Patient.model.Patient;
import com.patientservice.Patient.model.ValueObject.DateOfBirth;
import com.patientservice.Patient.model.ValueObject.Email;
import com.patientservice.Patient.model.ValueObject.Name;
import com.patientservice.Patient.model.ValueObject.PhoneNumber;
import com.patientservice.Patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientTests {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		patientRepository.deleteAll();
	}

	@Test
	public void testCreatePatient() {
		PatientDTO patientDTO = PatientDTO.builder()
				.name(Name.builder().firstName("Blond").lastName("Doe").build())
				.email(Email.builder().address("john.doe@example.com").build())
				.mobile(PhoneNumber.builder().number("0612345678").build())
				.dateOfBirth(DateOfBirth.builder().day(1).month(1).year(1997).build())
				.build();
		ResponseEntity<PatientDTO> responseEntity = restTemplate.postForEntity("/api/patient", patientDTO, PatientDTO.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();
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
		ResponseEntity<List<PatientDTO>> responseEntity = restTemplate.exchange("/api/patient", HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {});
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().size()).isEqualTo(3);
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
		ResponseEntity<PatientDTO> responseEntity = restTemplate.getForEntity("/api/patient/" + patient.getId(), PatientDTO.class);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().getId()).isEqualTo(patient.getId());
	}

}
