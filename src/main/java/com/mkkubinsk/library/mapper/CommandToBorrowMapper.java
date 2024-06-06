package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandToBorrowMapper {

    public Borrow fromCommand(CreateBorrowCommand createBorrowCommand) {
        return new Borrow(createBorrowCommand.getIdReader(),
                createBorrowCommand.getIdBook(),
                createBorrowCommand.getDateBorrow(),
                createBorrowCommand.getDateReturn());
    }
}
