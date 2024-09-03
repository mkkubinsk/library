package com.mkkubinsk.library.mapper;

import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.dto.ReaderDto;
import org.springframework.stereotype.Component;

@Component
public class ReaderToDtoMapper {

    public ReaderDto convertToDto(Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getSurname(),
                reader.getName(),
                reader.getStreet(),
                reader.getCity(),
                reader.getStreetNo()
        );
    }
}
