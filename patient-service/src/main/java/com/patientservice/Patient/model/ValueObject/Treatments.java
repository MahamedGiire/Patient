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


public class Treatments {
    private List<String> treatments;

    public Treatments(List<String> treatments) {
        this.treatments = Objects.requireNonNull(treatments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatments that = (Treatments) o;
        return treatments.equals(that.treatments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(treatments);
    }
}
