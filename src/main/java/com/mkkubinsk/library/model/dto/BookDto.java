package com.mkkubinsk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDto {
    private int id;
    private String title;
    private String author;
    private LocalDate releaseYear;

    public BookDto(int id, String title, String author, LocalDate releaseYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}


