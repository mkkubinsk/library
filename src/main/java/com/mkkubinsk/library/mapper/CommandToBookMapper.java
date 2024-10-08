package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.command.UpdateBookCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
public class CommandToBookMapper {

    public Book fromCommand(CreateBookCommand createBookCommand){
        return new Book(createBookCommand.getTitle(),
                createBookCommand.getAuthor(),
                createBookCommand.getReleaseYear());

    }

    public Book fromCommand(UpdateBookCommand updateBookCommand){
        return new Book(updateBookCommand.getTitle(),
                updateBookCommand.getAuthor(),
                updateBookCommand.getReleaseYear());

    }
}
