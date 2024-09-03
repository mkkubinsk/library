package com.mkkubinsk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowDto {
    private int id;
    private int idReader;
    private int idBook;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;

    public BorrowDto(int id, int idReader, int idBook, LocalDate dateBorrow, LocalDate dateReturn) {
        this.id = id;
        this.idReader = idReader;
        this.idBook = idBook;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
    }
}
