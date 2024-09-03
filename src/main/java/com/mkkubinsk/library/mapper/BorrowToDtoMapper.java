package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.dto.BorrowDto;
import org.springframework.stereotype.Component;

@Component
public class BorrowToDtoMapper {

    public BorrowDto convertToDto(Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getIdReader(),
                borrow.getIdBook(),
                borrow.getDateBorrow(),
                borrow.getDateReturn()
        );
    }
}
