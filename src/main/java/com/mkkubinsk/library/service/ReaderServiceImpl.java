package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReaderServiceImpl implements ReaderService {

    private ReaderRepository readerRepository;

    @Autowired
    public ReaderServiceImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    @Override
    public Reader findById(int id) {

        Optional<Reader> result = readerRepository.findById(id);
        Reader reader = null;

        if(result.isPresent()) {
            reader = result.get();
        } else {
            throw new RuntimeException("Did not find reader id - " + id);
        }

        return reader;
    }

    @Override
    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteById(int id) {
        readerRepository.deleteById(id);
    }
}
