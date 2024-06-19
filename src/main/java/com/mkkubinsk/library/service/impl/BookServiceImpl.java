package com.mkkubinsk.library.service.impl;

import com.mkkubinsk.library.mapper.CommandToBookMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    CommandToBookMapper commandToBookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CommandToBookMapper commandToBookMapper) {
        this.bookRepository = bookRepository;
        this.commandToBookMapper = commandToBookMapper;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        //TODO Add exception handling for Book findById
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book createBook(CreateBookCommand createBookCommand) {
        return bookRepository.save(commandToBookMapper.fromCommand(createBookCommand));
    }

    @Override
    public Book updateBook(int id, UpdateBookCommand updateBookCommand) {
        return null;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
