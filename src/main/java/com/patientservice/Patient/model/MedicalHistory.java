package com.patientservice.Patient.model;

import com.patientservice.Patient.model.Enums.Priority;
import com.patientservice.Patient.model.ValueObject.Description;
import com.patientservice.Patient.model.ValueObject.Diagnosis;
import com.patientservice.Patient.model.ValueObject.Medications;
import com.patientservice.Patient.model.ValueObject.Treatments;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Entity
@Table
@Builder
/**
 * The MedicalHistory Class holds the patient's medical records.
 */
public class MedicalHistory{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID recordsID; // The record id of the MedicalHistory

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient; // The patient id of the MedicalHistory

    @Embedded
    @Column(name = "description")
    private Description description; // The description of the MedicalHistory

    @Embedded
    @Column(name = "diagnosis")
    private Diagnosis diagnosis; // The diagnosis of the MedicalHistory

    @Embedded
    @Column(name = "treatments")
    private Treatments treatments; // The treatment of the MedicalHistory

    @Embedded
    @Column(name = "medications")
    private Medications medications; // The medications of the MedicalHistory

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "priority")
    @NotNull
    private Priority priority; // The priority of the MedicalHistory


    /**
     * Creates a new instance of the MedicalHistory class.
     *
     * @param recordsID The unique ID for this medical history record.
     * @param patient The patient associated with this medical history record.
     * @param description A description of the medical history.
     * @param diagnosis The diagnosis associated with this medical history.
     * @param treatments Any treatments prescribed for this medical history.
     * @param medications Any medications prescribed for this medical history.
     * @param priority the priority given to the medical history based on some checks.
     */
    public MedicalHistory(UUID recordsID, Patient patient, Description description, Diagnosis diagnosis, Treatments treatments, Medications medications
    ,Priority priority) {
        this.recordsID = generateRecordsID(recordsID);
        this.patient = patient;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.medications = medications;
        this.priority = priority;
    }

    /**
     * Default constructor for the MedicalHistory class.
     */
    public MedicalHistory() {
    }

    /**
     * Generates a new UUID for this medical history record.
     *
     * @param id An existing UUID, if one is available.
     * @return A new UUID, if one was not provided.
     */
    private UUID generateRecordsID(UUID id) {
        return id.randomUUID();
    }

    /**
     * Returns the unique ID for this medical history record.
     *
     * @return The recordsID.
     */
    public UUID getRecordsID() {
        return recordsID;
    }

    /**
     * Returns the patient associated with this medical history record.
     *
     * @return The patient.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns a description of the medical history.
     *
     * @return The description.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns the diagnosis associated with this medical history.
     *
     * @return The diagnosis.
     */
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    /**
     * Returns any treatments prescribed for this medical history.
     *
     * @return The treatments.
     */
    public Treatments getTreatments() {
        return treatments;
    }

    /**
     * Returns any medications prescribed for this medical history.
     *
     * @return The medications.
     */
    public Medications getMedications() {
        return medications;
    }

    /**
     * Returns the priority prescribed for this medical history.
     *
     * @return The priority.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the patient associated with this medical history record.
     *
     * @param patient The new patient.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Sets the priority associated with this medical history record.
     *
     * @param priority The new priority.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
