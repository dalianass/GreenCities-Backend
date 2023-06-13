package org.i4di.green.api.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom DTO used to encapsulate multiple {@link FieldErrorDTO} instances to be delivered back to the client.
 */
public class ApiErrorDTO implements Serializable {

    private List<FieldErrorDTO> errors;

    public ApiErrorDTO() {
        this.errors = new ArrayList<>();
    }

    public void addFieldError(String field, String message) {
        errors.add(new FieldErrorDTO(field, message));
    }

    public List<FieldErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorDTO> errors) {
        this.errors = errors;
    }

    public boolean isEmpty() {
        return errors.isEmpty();
    }

}
