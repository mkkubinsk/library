package com.mkkubinsk.library.model.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateBookCommand {

    @NotBlank(message = "Cannot be blank")
    private String title;

    @NotBlank(message = "Cannot be blank")
    private String author;

    @Past
    private LocalDate releaseYear;
}