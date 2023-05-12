package com.patientservice.Patient.model.Generator;

import java.util.UUID;

public class GenerateRandomUUID {
    public static UUID generate() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
