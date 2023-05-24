package com.patientservice.Appointment.Exceptions;

/**
 * Custom exception: DuplicateEntryException
 */
public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
