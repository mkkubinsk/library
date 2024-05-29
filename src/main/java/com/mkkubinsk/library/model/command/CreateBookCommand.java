package com.mkkubinsk.library.model.command;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookCommand {
    private String title;
    private String author;
    private int releaseYear;
}
