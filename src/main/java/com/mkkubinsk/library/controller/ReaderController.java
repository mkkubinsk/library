package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.command.CreateReaderCommand;
import com.mkkubinsk.library.model.dto.ReaderDto;
import com.mkkubinsk.library.repository.ReaderRepository;
import com.mkkubinsk.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderRepository readerRepository;
    private final ReaderService readerService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReaderDto> getAllReaders() {
        List<Reader> readerList = readerService.getAllReaders();
        return readerList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDto createReader(@RequestBody CreateReaderCommand createReaderCommand) {
        return convertToDto(readerService.createReader(createReaderCommand));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReaderDto getReaderById(@PathVariable int id) {
        return convertToDto(readerService.getReaderById(id));
    }

    private ReaderDto convertToDto(Reader reader) {
        return modelMapper.map(reader, ReaderDto.class);
    }

    private Reader convertToEntity(ReaderDto readerDto) {
        return modelMapper.map(readerDto, Reader.class);
    }

}
