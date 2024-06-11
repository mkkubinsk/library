package com.mkkubinsk.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Borrow {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int idReader;

    @Column(nullable = false)
    private int idBook;

    @Column(nullable = false)
    private LocalDate dateBorrow;

    @Column(nullable = false)
    private LocalDate dateReturn;

    @ManyToOne
    private Reader reader;

    @ManyToOne
    private Book book;

    public Borrow() {
    }

    public Borrow(int idReader, int idBook, LocalDate dateBorrow, LocalDate dateReturn) {
        this.idReader = idReader;
        this.idBook = idBook;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
    }

    public Borrow(int idReader, int idBook, LocalDate dateBorrow, LocalDate dateReturn,
                  Reader reader, Book book) {
        this.idReader = idReader;
        this.idBook = idBook;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.reader = reader;
        this.book = book;
//        this.reader.getBorrowList().add(this);
//        this.book.getBorrowList().add(this);
    }
}
