package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.model.command.UpdateBorrowCommand;

import java.util.List;

public interface BorrowService {

    List<Borrow> getAllBorrows();

    Borrow getBorrowById(int id);

    Borrow createBorrow(CreateBorrowCommand createBorrowCommand);

    Borrow updateBorrow(int id, UpdateBorrowCommand updateBorrowCommand);

    void deleteBorrow(int id);

}
