package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Embeddable
@Getter
@Builder
public class Category {

    private String department;

    public Category(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(department, category.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department);
    }
}
