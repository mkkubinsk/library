package com.mkkubinsk.library.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReaderCommand {
    private String surname;
    private String name;
    private String street;
    private String city;
    private String streetNo;
}