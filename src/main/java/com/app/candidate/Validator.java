package com.app.candidate;

import java.util.List;

/**
 * A generic interface for validating objects of type {@code T}.
 * <p>
 * Implementations of this interface are responsible for defining the logic
 * to validate objects and return a list of error messages if validation fails.
 * </p>
 *
 * @param <T> the type of object that this validator validates
 */
public interface Validator<T> {

    /**
     * Validates the given object and returns a list of error messages if the validation fails.
     * <p>
     * If the object is valid, the list should be empty. If there are validation errors,
     * the list will contain strings describing each error.
     * </p>
     *
     * @param t the object to validate
     * @return a list of validation error messages, or an empty list if the object is valid
     */
    List<String> validate(T t);

    /**
     * Validates the given object using the specified validator.
     * <p>
     * If the validator is {@code null}, this method throws an {@link IllegalArgumentException}.
     * If the validation fails (i.e., the list of errors is not empty), this method
     * throws an {@link IllegalArgumentException} with the error messages joined by new lines.
     * </p>
     *
     * @param <T>       the type of object being validated
     * @param t         the object to validate
     * @param validator the {@code Validator} instance to use for validation
     * @throws IllegalArgumentException if the validator is {@code null} or if validation fails
     */
    static <T> void validate(T t, Validator<T> validator) {

        if (validator == null) {
            throw new IllegalArgumentException("Validator cannot be null");
        }

        var errors = validator.validate(t);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}