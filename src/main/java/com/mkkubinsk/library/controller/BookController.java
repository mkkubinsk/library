package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.mapper.CommandToBookMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping
    public ResponseEntity getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/add")
    public ResponseEntity createBook(@RequestBody CreateBookCommand createBookCommand){
        return ResponseEntity.ok(bookService.createBook(createBookCommand));
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookById(@RequestParam int id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

}
