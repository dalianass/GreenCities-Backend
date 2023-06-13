package org.i4di.green.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * An exception handler component that processes the exceptions thrown by controllers.
 * <p>
 * The class is annotated with the {@link ControllerAdvice} annotation, which
 * indicates that annotated class assists a controller.
 */
@ControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorDTO processValidationError(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ObjectError> objectErrors = result.getGlobalErrors();

        return processFieldErrors(fieldErrors, objectErrors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorDTO processValidationError(ConstraintViolationException exception) {
        ApiErrorDTO response = new ApiErrorDTO();

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();

            response.addFieldError(propertyPath, message);
        }

        return response;
    }

    /**
     * Processes the given {@link FieldError}s and wraps them into the {@link FieldErrorDTO}
     * instances encapsulated within the {@link ApiErrorDTO} response.
     *
     * @param fieldErrors  Field errors to process.
     * @param objectErrors Object errors to process.
     * @return {@link ApiErrorDTO} as a response, with error messages.
     */
    private ApiErrorDTO processFieldErrors(List<FieldError> fieldErrors, List<ObjectError> objectErrors) {
        ApiErrorDTO response = new ApiErrorDTO();

        for (FieldError error : fieldErrors) {
            response.addFieldError(error.getField(), error.getDefaultMessage());
        }

        for (ObjectError error : objectErrors) {
            response.addFieldError(error.getObjectName().replace("DTO", ""), error.getDefaultMessage());
        }

        return response;
    }

}
