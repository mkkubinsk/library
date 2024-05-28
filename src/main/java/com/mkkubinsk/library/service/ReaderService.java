package com.mkkubinsk.library.service;

import com.mkkubinsk.library.model.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> findAll();

    Reader findById(int id);

    Reader save(Reader reader);

    void deleteById(int id);

}
