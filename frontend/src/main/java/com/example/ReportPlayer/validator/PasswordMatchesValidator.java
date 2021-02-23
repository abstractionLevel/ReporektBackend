package com.example.ReportPlayer.validator;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    private String password;
    private String confirmPassword;
    private String message;

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        password = constraintAnnotation.password();
        confirmPassword = constraintAnnotation.confirmPassword();
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object o ,ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try
        {
            final Object  firstObj = BeanUtils.getProperty(o, password);
            final Object  secondObj = BeanUtils.getProperty(o, confirmPassword);
            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        if (!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
