package com.patientservice.Patient.model;

import com.patientservice.Patient.model.ValueObject.*;
import jakarta.persistence.*;
import lombok.Builder;

/**
 * The Patient Class saves the information of the patient
 *
 */
@Entity
@Builder
@Table

public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // The id of Patient

    @Column(name = "name")
    @Embedded
    private Name name; // The name of Patient

    @Column(name = "email")
    @Embedded
    private Email email; // The email of Patient

    @Column(name = "number")
    @Embedded
    private PhoneNumber mobile; // The mobile of Patient

    @Column(name = "dateOfBirth")
    @Embedded
    private DateOfBirth dateOfBirth; // The dateOfBirth of Patient

    /**
     * Constructor for the patient
     *
     * @param id The unique ID for this patient.
     * @param name The name of the patient.
     * @param email The email address for the patient.
     * @param mobile The mobile phone number for the patient.
     * @param dateOfBirth The date of birth for the patient.
     */
    public Patient(Long id,Name name, Email email, PhoneNumber mobile, DateOfBirth dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Default constructor for the Patient class.
     */
    public Patient() {
    }

    /**
     * Returns the unique ID for this patient.
     *
     * @return The ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the name of the patient.
     *
     * @return The name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the email address for the patient.
     *
     * @return The email address.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the mobile phone number for the patient.
     *
     * @return The phone number.
     */
    public PhoneNumber getMobile() {
        return mobile;
    }

    /**
     * Returns the date of birth for the patient.
     *
     * @return The date of birth.
     */
    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the name of the patient.
     *
     * @param name The new name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Sets the email address for the patient.
     *
     * @param email The new email address.
     */
    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * Sets the mobile phone number for the patient.
     *
     * @param mobile The new phone number.
     */
    public void setMobile(PhoneNumber mobile) {
        this.mobile = mobile;
    }

    /**
     * Sets the date of birth for the patient.
     *
     * @param dateOfBirth The new date of birth.
     */
    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

