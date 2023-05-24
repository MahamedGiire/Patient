package com.patientservice.Appointment.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Embeddable
@Getter
@Builder

public class Doctor {

    private String firstName;
    private String lastName;

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
