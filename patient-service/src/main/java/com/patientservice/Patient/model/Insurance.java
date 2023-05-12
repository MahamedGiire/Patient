package com.patientservice.Patient.model;

import com.patientservice.Patient.model.ValueObject.*;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

/**
 * The Insurance class is the insurance of the Patient.
 *
 */

@Entity
@Table

public class Insurance {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // The id of Insurance

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient; // The id of Patient

    @Column(name = "policy")
    @Embedded
    private Policy policy; // The PolicyNmr of Insurance

    @Column(name = "provider")
    @Embedded
    private Provider provider; // The Provider of Insurance

    @Column(name = "premium")
    @Embedded
    private Money premium; // The Cost/Premium of Insurance

    @Column(name = "coverage")
    @Embedded
    private Coverage coverage; // The Coverage of Insurance

    @Column(name = "startDate")
    private LocalDate startDate; // The StartDate of Insurance

    @Column(name = "endDate")
    private LocalDate endDate; // The EndDate of Insurance

    /**
     * Constructor for the insurance
     *
     * @param id The unique ID for this insurance policy.
     * @param patient The patient associated with this policy.
     * @param policy The type of insurance policy.
     * @param provider The insurance provider.
     * @param premium The premium to be paid for this policy.
     * @param coverage The coverage provided by this policy.
     * @param startDate The start date of the policy.
     * @param endDate The end date of the policy.
     */
    public Insurance(Long id, Patient patient, Policy policy, Provider provider, Money premium, Coverage coverage, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.patient = patient;
        this.policy = policy;
        this.provider = provider;
        this.premium = premium;
        this.coverage = coverage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Default constructor for the Insurance class.
     */
    public Insurance() {
    }

    /**
     * Returns the unique ID for this insurance policy.
     *
     * @return The ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the patient associated with this policy.
     *
     * @return The patient.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the type of insurance policy.
     *
     * @return The policy.
     */
    public Policy getPolicy() {
        return policy;
    }

    /**
     * Returns the insurance provider.
     *
     * @return The provider.
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Returns the premium to be paid for this policy.
     *
     * @return The premium.
     */
    public Money getPremium() {
        return premium;
    }

    /**
     * Returns the coverage provided by this policy.
     *
     * @return The coverage.
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * Returns the start date of the policy.
     *
     * @return The start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the policy.
     *
     * @return The end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
