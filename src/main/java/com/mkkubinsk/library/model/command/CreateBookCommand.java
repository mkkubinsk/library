package com.mkkubinsk.library.model.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateBookCommand {
    private String title;
    private String author;
    private LocalDate releaseYear;
}