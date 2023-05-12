package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@Embeddable
@Getter
@Builder

/**
 * The Medications value object in the Medical History entity.
 */
public class Medications {
    private List<String> medications;

    public Medications(List<String> medications) {
        this.medications = Objects.requireNonNull(medications);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medications that = (Medications) o;
        return medications.equals(that.medications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medications);
    }
}
