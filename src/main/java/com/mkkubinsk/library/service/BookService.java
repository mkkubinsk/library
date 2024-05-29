package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(int id);

    Book createBook(CreateBookCommand createBookCommand);

    Book updateBook(int id, UpdateBookCommand updateBookCommand);

    void deleteBook (int id);

}
