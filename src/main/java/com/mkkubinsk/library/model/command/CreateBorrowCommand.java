package com.mkkubinsk.library.model.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateBorrowCommand {

    @NotBlank(message = "Cannot be blank")
    private int idReader;

    @NotBlank(message = "Cannot be blank")
    private int idBook;

    @Past
    private LocalDate dateBorrow;

    @NotBlank(message = "Cannot be blank")
    private LocalDate dateReturn;
}
