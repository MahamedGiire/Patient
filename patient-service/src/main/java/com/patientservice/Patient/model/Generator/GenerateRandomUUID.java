package com.patientservice.Patient.model.Generator;

import java.util.UUID;

/**
 * Generate a Random UUID
 *
 * Is a bit cleaner to call the generate() method, than randomUUID in the code.
 *
 * @return a random UUID
 */
public class GenerateRandomUUID {
    public static UUID generate() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
