package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.repository.BorrowRepository;
import com.mkkubinsk.library.service.BookService;
import com.mkkubinsk.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowRepository borrowRepository;
    private final BorrowService borrowService;

    //TODO: Constructor??

    @GetMapping
    public ResponseEntity getAllBorrows(){
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }

    @PostMapping("/add")
    public ResponseEntity createBorrow(@RequestBody CreateBorrowCommand createBorrowCommand) {
        return ResponseEntity.ok(borrowService.createBorrow(createBorrowCommand));
    }

    @GetMapping("/{id}")
    public ResponseEntity getBorrowById(@PathVariable int id) {
        return ResponseEntity.ok(borrowService.getBorrowById(id));
    }
}
