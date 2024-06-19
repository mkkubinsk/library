package com.mkkubinsk.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkkubinsk.library.LibraryApplication;
import com.mkkubinsk.library.model.Borrow;
import com.mkkubinsk.library.model.command.CreateBorrowCommand;
import com.mkkubinsk.library.model.dto.BorrowDto;
import com.mkkubinsk.library.repository.BorrowRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LibraryApplication.class})
@AutoConfigureMockMvc
class BorrowControllerTest {

    private MockMvc postman;
    private BorrowRepository borrowRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public BorrowControllerTest(MockMvc postman, BorrowRepository borrowRepository, ObjectMapper objectMapper) {
        this.postman = postman;
        this.borrowRepository = borrowRepository;
        this.objectMapper = objectMapper;
    }

    @Test
    void shouldCreateSingleBook() throws Exception {
        // Given
        CreateBorrowCommand command = new CreateBorrowCommand();
        command.setIdBook(1);
        command.setIdReader(1);
        command.setDateBorrow(LocalDate.of(2024, 1, 1));
        command.setDateReturn(LocalDate.of(2024, 2, 2));

        // When
        String responseJson = postman.perform(post("/borrow/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idBook").value(1))
                .andExpect(jsonPath("$.idReader").value(1))
                .andExpect(jsonPath("$.dateBorrow").value("2024-01-01"))
                .andExpect(jsonPath("$.dateReturn").value("2024-02-02"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        BorrowDto response = objectMapper.readValue(responseJson, BorrowDto.class);
        Borrow borrowExpected = borrowRepository.findById(response.getId()).get();

        Assertions.assertEquals(response.getDateBorrow(), borrowExpected.getDateBorrow());
    }

    public int testBorrow() {
        return borrowRepository.saveAndFlush(new Borrow(
                1,
                2,
                LocalDate.of(2024,1,1),
                LocalDate.of(2024,1,2)
        )).getId();
    }

    @Test
    void shouldGetSingleBorrow() throws Exception {
        int testBorrowId = testBorrow();

        postman.perform(get("/borrow/" + testBorrowId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateBorrow").value("2024-01-01"));
    }

    @Test
    void shouldGetAllBorrows() throws Exception {
        // Given
        testBorrowList();

        // When
        String responseJson = postman.perform(get("/borrow"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idReader").value(1))
                .andExpect(jsonPath("$[0].idBook").value(2))
                .andExpect(jsonPath("$[0].dateBorrow").value("2024-01-01"))
                .andExpect(jsonPath("$[0].dateReturn").value("2024-01-02"))
                .andExpect(jsonPath("$[1].idReader").value(3))
                .andExpect(jsonPath("$[1].idBook").value(4))
                .andExpect(jsonPath("$[1].dateBorrow").value("2024-01-03"))
                .andExpect(jsonPath("$[1].dateReturn").value("2024-01-04"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then

        List<BorrowDto> response = objectMapper.readValue(responseJson, new TypeReference<List<BorrowDto>>() {});
        List<Borrow> borrowListExpected = borrowRepository.findAll();

        Assert.assertEquals(
                borrowListExpected.stream()
                        .map(Borrow::getDateBorrow)
                        .toList(),
                response.stream()
                        .map(BorrowDto::getDateBorrow)
                        .toList()
        );
    }

    public List<Integer> testBorrowList() {
        List<Borrow> inputBorrowList = new ArrayList<>(
                Arrays.asList(
                        new Borrow(1, 2, LocalDate.of(2024,1,1), LocalDate.of(2024,1,2)),
                        new Borrow(3, 4, LocalDate.of(2024,1,3), LocalDate.of(2024,1,4))
                )
        );

        borrowRepository.saveAllAndFlush(inputBorrowList);

        return inputBorrowList.stream()
                .map(Borrow::getId)
                .toList();
    }
}