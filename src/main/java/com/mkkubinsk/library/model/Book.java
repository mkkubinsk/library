package com.mkkubinsk.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name="book")  //TODO: remove if works without
@Getter
@Setter
@ToString (exclude="borrowList")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private LocalDate releaseYear;

    @OneToMany
    private List<Borrow> borrowList = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String author, LocalDate releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

}
