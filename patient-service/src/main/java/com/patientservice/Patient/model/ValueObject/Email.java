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
 * The Email value object in the Patient entity
 */
public class Email {
    private String address;

    public Email(String address) {
        if (address == null || !isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid email address: " + address);
        }
        this.address = address;
    }

    private boolean isValidAddress(String address) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return address.matches(regex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
