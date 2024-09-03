package com.mkkubinsk.library.service.impl;

import com.mkkubinsk.library.mapper.CommandToReaderMapper;
import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.command.CreateReaderCommand;
import com.mkkubinsk.library.model.command.UpdateReaderCommand;
import com.mkkubinsk.library.repository.ReaderRepository;
import com.mkkubinsk.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReaderServiceImpl implements ReaderService {

    ReaderRepository readerRepository;
    CommandToReaderMapper commandToReaderMapper;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository, CommandToReaderMapper commandToReaderMapper) {
        this.readerRepository = readerRepository;
        this.commandToReaderMapper = commandToReaderMapper;
    }

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Reader getReaderById(int id) {
        //TODO Add exception handling for Reader findById
        return readerRepository.findById(id).orElseThrow();
    }

    @Override
    public Reader createReader(CreateReaderCommand createReaderCommand) {
        return readerRepository.save(commandToReaderMapper.fromCommand(createReaderCommand));
    }

    @Override
    public Reader updateReader(int id, UpdateReaderCommand updateReaderCommand) {
        return null;
    }

    @Override
    public void deleteReader(int id) {
        readerRepository.deleteById(id);
    }
}
