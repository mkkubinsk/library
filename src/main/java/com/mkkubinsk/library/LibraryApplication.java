package com.mkkubinsk.library;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.repository.BookRepository;
import com.mkkubinsk.library.repository.BorrowRepository;
import com.mkkubinsk.library.repository.ReaderRepository;
import com.mkkubinsk.library.service.BookService;
import com.mkkubinsk.library.service.FileImportService;
import com.mkkubinsk.library.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class LibraryApplication {

	private FileImportService fileImportService;
	private BookRepository bookRepository;
	private ReaderRepository readerRepository;
	private BorrowRepository borrowRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Autowired
	public LibraryApplication(FileImportService fileImportService, BookRepository bookRepository,
							  ReaderRepository readerRepository, BorrowRepository borrowRepository) {
		this.fileImportService = fileImportService;
		this.bookRepository = bookRepository;
		this.readerRepository = readerRepository;
		this.borrowRepository = borrowRepository;
	}

	@Bean
	public CommandLineRunner commandLineRunner(String[] args) {
		return runner -> {
			List<Book> bookList = fileImportService.getBookListFromFile();
			List<Reader> readerList = fileImportService.getReaderListFromFile();
			bookRepository.saveAll(bookList);
			readerRepository.saveAll(readerList);
			List<Borrow> borrowList = fileImportService.getBorrowListFromFile();
			borrowRepository.saveAll(borrowList);
		};
	}

}
