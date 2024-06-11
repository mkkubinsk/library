package com.mkkubinsk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDto {
    private String title;
    private String author;
    private LocalDate releaseYear;
}
