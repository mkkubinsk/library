package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.command.CreateReaderCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandToReaderMapper {

    public Reader fromCommand(CreateReaderCommand createReaderCommand) {
        return new Reader(createReaderCommand.getSurname(),
                createReaderCommand.getName(),
                createReaderCommand.getStreet(),
                createReaderCommand.getCity(),
                createReaderCommand.getStreetNo());
    }
}
