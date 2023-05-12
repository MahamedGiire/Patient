package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Embeddable
@Getter
@Builder

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
}
