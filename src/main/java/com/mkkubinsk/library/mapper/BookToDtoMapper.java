package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookToDtoMapper {

    public BookDto convertToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseYear());
    }

}
