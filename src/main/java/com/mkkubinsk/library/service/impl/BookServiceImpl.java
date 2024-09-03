package com.mkkubinsk.library.service.impl;

import com.mkkubinsk.library.exception.NotFoundException;
import com.mkkubinsk.library.mapper.CommandToBookMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
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

        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new NotFoundException("id: " + id);
        }

        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book createBook(CreateBookCommand createBookCommand) {
        return bookRepository.save(commandToBookMapper.fromCommand(createBookCommand));
    }

    @Override
    public Book updateBook(int id, CreateBookCommand createBookCommand) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setAuthor(createBookCommand.getAuthor());
                    book.setTitle(createBookCommand.getTitle());
                    book.setReleaseYear(createBookCommand.getReleaseYear());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    return bookRepository.save(commandToBookMapper.fromCommand(createBookCommand));
                });
    }

    @Override
    public Book patchBook(int id, UpdateBookCommand updateBookCommand) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updateBookCommand.getTitle() != null ? updateBookCommand.getTitle() : book.getTitle());
                    book.setAuthor(updateBookCommand.getAuthor() != null ? updateBookCommand.getAuthor() : book.getAuthor());
                    book.setReleaseYear(updateBookCommand.getReleaseYear() != null ? updateBookCommand.getReleaseYear() : book.getReleaseYear());
                    return bookRepository.save(book);
                })
                .get();
    }

    // TODO: prepare custom exception handler for NoSuchElementException - if book not found

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
