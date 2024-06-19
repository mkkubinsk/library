package com.mkkubinsk.library.service.impl;

import com.mkkubinsk.library.mapper.CommandToBorrowMapper;
import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.model.command.UpdateBorrowCommand;
import com.mkkubinsk.library.repository.BorrowRepository;
import com.mkkubinsk.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    BorrowRepository borrowRepository;
    CommandToBorrowMapper commandToBorrowMapper;

    @Autowired
    public BorrowServiceImpl(BorrowRepository borrowRepository, CommandToBorrowMapper commandToBorrowMapper) {
        this.borrowRepository = borrowRepository;
        this.commandToBorrowMapper = commandToBorrowMapper;
    }

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow getBorrowById(int id) {
        //TODO Add exception handling for Borrow findById
        return borrowRepository.findById(id).orElseThrow();
    }

    @Override
    public Borrow createBorrow(CreateBorrowCommand createBorrowCommand) {
        return borrowRepository.save(commandToBorrowMapper.fromCommand(createBorrowCommand));
    }

    @Override
    public Borrow updateBorrow(int id, UpdateBorrowCommand updateBorrowCommand) {
        return null;
    }

    @Override
    public void deleteBorrow(int id) {
        borrowRepository.deleteById(id);
    }
}
