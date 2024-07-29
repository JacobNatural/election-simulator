package com.app.candidate;

import java.util.List;

/**
 * Interface for Validator classes.
 * Defines a single method signature for validating an object of type T.
 */
public interface Validator<T>{

    /**
     * Validates an object of type T.
     * @param t the object to validate.
     * @return List<String> if object t is valid, the list will be empty. If not, list will contain error messages.
     */
    List<String> validate(T t);

    /**
     * Static helper method to validate an object using a specific Validator implementor.
     * If validation fails, it throws an exception.
     * @param t the object to validate.
     * @param validator the validator instance to use.
     * @throws IllegalArgumentException if the validator instance is null or the object t fails the validation.
     */
    static <T> void validate(T t, Validator<T> validator) {

        if(validator == null){
            throw new IllegalArgumentException("Validator cannot be null");
        }

        var errors = validator.validate(t);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}
