package com.mkkubinsk.library.model.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateBorrowCommand {
    private int idReader;
    private int idBook;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;
}
