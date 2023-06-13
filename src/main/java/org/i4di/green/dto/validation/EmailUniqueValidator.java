package org.i4di.green.dto.validation;

import org.i4di.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {
    private final UserService userService;

    @Autowired
    public EmailUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(EmailUnique constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.showByEmail(email).isPresent();
    }
}
