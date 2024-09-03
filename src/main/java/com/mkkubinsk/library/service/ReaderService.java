package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.CreateReaderCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import com.mkkubinsk.library.model.command.UpdateReaderCommand;

import java.util.List;

public interface ReaderService {

    List<Reader> getAllReaders();

    Reader getReaderById(int id);

    Reader createReader(CreateReaderCommand createReaderCommand);

    Reader updateReader(int id, UpdateReaderCommand updateReaderCommand);

    void deleteReader (int id);

}
