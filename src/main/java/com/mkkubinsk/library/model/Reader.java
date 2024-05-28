package com.mkkubinsk.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude="borrowList")
public class Reader {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String streetNo;

    @OneToMany
    private List<Borrow> borrowList = new ArrayList<>();

    public Reader() {
    }

    public Reader(String surname, String name, String street, String city, String streetNo) {
        this.surname = surname;
        this.name = name;
        this.street = street;
        this.city = city;
        this.streetNo = streetNo;
    }
}
