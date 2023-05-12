package com.patientservice.Patient.model.ValueObject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.regex.Pattern;

@NoArgsConstructor
@Embeddable
@Getter
@Builder

public class PhoneNumber {
    private String number;

    private static final Pattern DUTCH_PHONE_NUMBERS = Pattern.compile("\\+31\\s\\d{2}\\s\\d{7}|\\+31\\s\\d{3}\\s\\d{6}|\\d{10}");

    public PhoneNumber(String number) {
        if (!isValid(number)) {
            throw new IllegalArgumentException("Invalid phone number.");
        }
        this.number = number;
    }

    public boolean isValid(String number) {
        return DUTCH_PHONE_NUMBERS.matcher(number).matches();
    }
}
