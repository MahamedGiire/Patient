package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Embeddable
@Getter
@Builder

/**
 * The Diagnosis value object in the Medical History entity.
 */
public class Diagnosis {
    private String code;
    private String name;

    public Diagnosis(String code, String name) {
        if (code == null || name == null) {
            throw new IllegalArgumentException("Code and name cannot be null");
        }
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return code.equals(diagnosis.code) && name.equals(diagnosis.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
