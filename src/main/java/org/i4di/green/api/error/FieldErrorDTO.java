package org.i4di.green.api.error;

import java.io.Serializable;

/**
 * A custom DTO to carry the information on field errors to the client.
 */
public class FieldErrorDTO implements Serializable {

    private String field;
    private String message;

    public FieldErrorDTO() {}

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
