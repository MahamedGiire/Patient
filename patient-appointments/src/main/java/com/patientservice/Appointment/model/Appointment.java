package com.patientservice.Appointment.model;

import com.patientservice.Appointment.model.ValueObject.Category;
import com.patientservice.Appointment.model.ValueObject.Doctor;
import com.patientservice.Appointment.model.ValueObject.Hospital;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Appointment Class saves the information of the appointment
 *
 */
@Entity
@Builder
@Table
public class Appointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // The id of Appointment

    @Column(name = "patient_id")
    private Long patientId; // The id of Patient, We add this through RestTemplate

    @Column(name = "appointmentDate")
    private LocalDate appointmentDate; // The date of the appointment

    @Column(name = "doctor")
    @Embedded
    private Doctor doctor; // The doctor of the appointment

    @Column(name = "hospital")
    @Embedded
    private Hospital hospital; // The hospital of the appointment

    @Column(name = "category")
    @Embedded
    private Category category; // The category of the appointment

    /**
     * Constructor for the appointment
     *
     * @param id The id of Appointment.
     * @param appointmentDate The date of the Appointment.
     * @param doctor The doctor of the Appointment.
     * @param hospital The hospital of the Appointment.
     * @param category The category of the Appointment.
     */

    public Appointment(Long id, Long patientId, LocalDate appointmentDate, Doctor doctor, Hospital hospital, Category category) {
        this.id = id;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
        this.hospital = hospital;
        this.category = category;
    }

    /**
     * Default constructor for the Appointment class.
     */
    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
