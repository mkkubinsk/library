package com.mkkubinsk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowDto {
    private int idReader;
    private int idBook;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;
}
