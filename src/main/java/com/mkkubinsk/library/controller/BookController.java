package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.mapper.BookToDtoMapper;
import com.mkkubinsk.library.mapper.CommandToBookMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import com.mkkubinsk.library.model.dto.BookDto;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookToDtoMapper bookToDtoMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks(){
        List<Book> bookList = bookService.getAllBooks();
        return bookList.stream()
                .map(bookToDtoMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable int id){
        return bookToDtoMapper.convertToDto(bookService.getBookById(id));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@Valid @RequestBody CreateBookCommand createBookCommand){
        return bookToDtoMapper.convertToDto(bookService.createBook(createBookCommand));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BookDto updateBook(@Valid @RequestBody CreateBookCommand createBookCommand,
                              @PathVariable int id) {
        return bookToDtoMapper.convertToDto(
                bookService.updateBook(id, createBookCommand)
        );
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<BookDto> patchBook(@PathVariable int id,
                                             @RequestBody UpdateBookCommand updateBookCommand) {
        return ResponseEntity.ok(bookToDtoMapper.convertToDto(
                bookService.patchBook(id, updateBookCommand)
        ));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }

}
