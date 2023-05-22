package com.patientservice.Appointment.Design;

import com.patientservice.Appointment.dto.AppointmentDTO;
import com.patientservice.Appointment.model.Appointment;
import com.patientservice.Appointment.repository.AppointmentRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Builder
@Slf4j

@Component
public class Factory {

    @Autowired
    private final AppointmentRepository appointmentRepository;

    /**
     * Creates a new instance of the Appointment class.
     *
     * @param appointmentDTO pass the DTO of the appointment entity
     *
     * @return the appointment
     */
    public Appointment createAppointment(AppointmentDTO appointmentDTO){

        Appointment appointment = new Appointment(appointmentDTO.getId(), appointmentDTO.getAppointmentDate(),
                                                appointmentDTO.getDoctor(), appointmentDTO.getHospital(), appointmentDTO.getCategory());

        return appointment;

    }
}
