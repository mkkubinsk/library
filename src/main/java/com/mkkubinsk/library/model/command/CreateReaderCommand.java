package com.mkkubinsk.library.model.command;

import com.mkkubinsk.library.validation.annotation.ColorTypeValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ColorTypeValidation
public class CreateReaderCommand {

//    @NotBlank(message = "Cannot be blank")
    private String surname;

    @NotBlank(message = "Cannot be blank")
    private String name;

    @NotBlank(message = "Cannot be blank")
    private String street;

    @NotBlank(message = "Cannot be blank")
    private String city;

    @NotBlank(message = "Cannot be blank")
    private String streetNo;
}