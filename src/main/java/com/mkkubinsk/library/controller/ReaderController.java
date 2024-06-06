package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.model.command.CreateReaderCommand;
import com.mkkubinsk.library.repository.ReaderRepository;
import com.mkkubinsk.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;

    //TODO: Constructor?

    @GetMapping
    public ResponseEntity getAllReaders() {
        return ResponseEntity.ok(readerService.getAllReaders());
    }

    @PostMapping("/add")
    public ResponseEntity createReader(@RequestBody CreateReaderCommand createReaderCommand) {
        return ResponseEntity.ok(readerService.createReader(createReaderCommand));
    }

    @GetMapping("/{id}")
    public ResponseEntity getReaderById(@PathVariable int id) {
        return ResponseEntity.ok(readerService.getReaderById(id));
    }

}
