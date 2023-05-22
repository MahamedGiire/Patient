package com.patientservice.Appointment.controller;

import com.patientservice.Appointment.dto.AppointmentDTO;
import com.patientservice.Appointment.model.Appointment;
import com.patientservice.Appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * Create a appointment
     *
     * @param appointmentDTO pass the appointment dto
     *
     * @return ResponseEntity, with the appointmentDTO.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Appointment> createPatient(@RequestBody AppointmentDTO appointmentDTO){
        try {
            this.appointmentService.createAppointment(appointmentDTO);
            return new ResponseEntity(appointmentDTO, HttpStatus.CREATED);
        } catch(Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all appointments
     *
     * @return List, of all appointments
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getAllPatienten(){
        return appointmentService.getAllAppointments();
    }
}
