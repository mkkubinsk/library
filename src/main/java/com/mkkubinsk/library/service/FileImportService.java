package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

@Component
public class FileImportService {

    private BookRepository bookRepository;
    private ReaderRepository readerRepository;

    @Autowired
    public FileImportService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public List<Book> getBookListFromFile() throws IOException {

        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        return Files.lines(Paths.get("ksiazki.txt"))
                .map(l -> l.split(";"))
                .map(w -> new Book(
                        w[1],
                        w[2],
                        LocalDate.parse(w[3], format)
                ))
                .toList();
    }

    public List<Reader> getReaderListFromFile() throws IOException {
        return Files.lines(Paths.get("czytelnicy.txt"))
                .map(l -> l.split(";"))
                .map(w -> new Reader(
                        w[1].split(" ")[1],
                        w[1].split(" ")[0],
                        w[2],
                        w[3],
                        w[4]
                ))
                .toList();
    }

    public List<Borrow> getBorrowListFromFile() throws IOException {

        // + 1 offset in book, reader id -> H2 starts to count from 1, not 0
        return Files.lines(Paths.get("wypozyczenia.txt"))
                .map(l -> l.split(";"))
                .map(w -> new Borrow(
                        Integer.parseInt(w[1]) + 1,
                        Integer.parseInt(w[2]) + 1,
                        LocalDate.parse(w[3]),
                        LocalDate.parse(w[4]),
                        readerRepository.findById(Integer.parseInt(w[1]) + 1).orElseThrow(),
                        bookRepository.findById(Integer.parseInt(w[2]) + 1).orElseThrow()
                ))
                .toList();
    }
}
