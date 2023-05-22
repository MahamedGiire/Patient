package com.patientservice.Appointment.service;

import com.patientservice.Appointment.Design.Factory;
import com.patientservice.Appointment.dto.AppointmentDTO;
import com.patientservice.Appointment.model.Appointment;
import com.patientservice.Appointment.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private final Factory factory;

    /**
     *
     * Create a Appointment.
     *
     * @param appointmentDTO Pass the DTO to the method.
     *
     * Method uses the Factory class to create a new instance of appointment entity.
     */
    @Transactional
    public void createAppointment(AppointmentDTO appointmentDTO) {
        try {
            Appointment appointment = factory.createAppointment(appointmentDTO);
            appointmentRepository.save(appointment);
        }  catch (com.patientservice.Appointment.Exceptions.DuplicateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new com.patientservice.Appointment.Exceptions.DatabaseOperationException("Error creating patient in database", e);
        }
    }

    /**
     * Returns the patients in our DB
     *
     * @return List of patients
     */
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(this::mapToAppointmentDTO).toList();
    }

    /**
     *
     * Map the DTO to the Appointment Entity.
     *
     * @param appointment Pass the entity to method
     *
     * @return Appointment DTO build with the Appointment Data
     */
    private AppointmentDTO mapToAppointmentDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .doctor(appointment.getDoctor())
                .hospital(appointment.getHospital())
                .category(appointment.getCategory())
                .build();
    }
}
