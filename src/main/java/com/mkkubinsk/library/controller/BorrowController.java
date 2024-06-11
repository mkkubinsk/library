package com.mkkubinsk.library.controller;

import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.model.dto.BookDto;
import com.mkkubinsk.library.model.dto.BorrowDto;
import com.mkkubinsk.library.repository.BorrowRepository;
import com.mkkubinsk.library.service.BookService;
import com.mkkubinsk.library.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowRepository borrowRepository;
    private final BorrowService borrowService;
    private final ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BorrowDto> getAllBorrows(){
        List<Borrow> borrowList = borrowService.getAllBorrows();
        return borrowList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public BorrowDto createBorrow(@RequestBody CreateBorrowCommand createBorrowCommand) {
        return convertToDto(borrowService.createBorrow(createBorrowCommand));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowDto getBorrowById(@PathVariable int id) {
        return convertToDto(borrowService.getBorrowById(id));
    }

    private BorrowDto convertToDto(Borrow borrow) {
        return modelMapper.map(borrow, BorrowDto.class);
    }

    private Borrow convertToEntity(BorrowDto borrowDto) {
        return modelMapper.map(borrowDto, Borrow.class);
    }
}
