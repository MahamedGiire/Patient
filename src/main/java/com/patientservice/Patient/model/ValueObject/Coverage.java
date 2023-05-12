package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Builder

public class Coverage {
    private String coverageType;
    private Double coverageLimit;

    public Coverage(String coverageType, Double coverageLimit) {
        this.coverageType = coverageType;
        this.coverageLimit = coverageLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coverage coverage = (Coverage) o;
        return Objects.equals(coverageType, coverage.coverageType) &&
                Objects.equals(coverageLimit, coverage.coverageLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverageType, coverageLimit);
    }
}
