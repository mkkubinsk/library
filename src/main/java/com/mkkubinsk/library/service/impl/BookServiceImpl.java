package com.mkkubinsk.library.service.impl;

import com.mkkubinsk.library.mapper.CommandToBookMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommandToBookMapper commandToBookMapper;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {

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

    }
}
