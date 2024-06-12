package com.mkkubinsk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaderDto {
    private int id;
    private String surname;
    private String name;
    private String street;
    private String city;
    private String streetNo;

    public ReaderDto(int id, String surname, String name, String street, String city, String streetNo) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.street = street;
        this.city = city;
        this.streetNo = streetNo;
    }
}