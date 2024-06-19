package com.mkkubinsk.library.validation.impl;

import com.mkkubinsk.library.model.command.CreateReaderCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ColorTypeValidator implements ConstraintValidator<com.mkkubinsk.library.validation.annotation.ColorTypeValidation, CreateReaderCommand>
{
    @Override
    public boolean isValid(CreateReaderCommand createReaderCommand, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"RED","GREEN","BLUE"});
        return list.contains(createReaderCommand.getSurname());
    }

    @Override
    public void initialize(com.mkkubinsk.library.validation.annotation.ColorTypeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
