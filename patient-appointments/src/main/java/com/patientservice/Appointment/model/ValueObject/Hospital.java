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
public class Hospital {

    private String name;
    private String location;

    public Hospital(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(name, hospital.name) &&
                Objects.equals(location, hospital.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
