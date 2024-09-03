package com.mkkubinsk.library.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkkubinsk.library.LibraryApplication;
import com.mkkubinsk.library.model.Reader;
import com.mkkubinsk.library.model.command.CreateReaderCommand;
import com.mkkubinsk.library.model.dto.ReaderDto;
import com.mkkubinsk.library.repository.ReaderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LibraryApplication.class})
@AutoConfigureMockMvc
class ReaderControllerTest {

    private MockMvc postman;
    private ReaderRepository readerRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public ReaderControllerTest(MockMvc postman, ReaderRepository readerRepository, ObjectMapper objectMapper) {
        this.postman = postman;
        this.readerRepository = readerRepository;
        this.objectMapper = objectMapper;
    }

    @Test
    void shouldCreateSingleReader() throws Exception {
        // Given
        CreateReaderCommand command = new CreateReaderCommand();
        command.setSurname("testSurname");
        command.setName("testName");
        command.setStreet("testStreet");
        command.setStreetNo("666");
        command.setCity("testCity");

        // When
        String responseJson = postman.perform(post("/reader/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(command)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.surname").value("testSurname"))
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.street").value("testStreet"))
                .andExpect(jsonPath("$.streetNo").value("666"))
                .andExpect(jsonPath("$.city").value("testCity"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        ReaderDto response = objectMapper.readValue(responseJson, ReaderDto.class);
        Reader readerExpected = readerRepository.findById(response.getId()).get();

        Assertions.assertEquals(response.getSurname(), readerExpected.getSurname());
    }

    @Test
    void shouldGetSingleReader() throws Exception {
        int testReaderId = testReader();

        postman.perform(get("/reader/" + testReaderId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("ReaderSurname"));
    }

    @Test
    void shouldGetAllReaders() throws Exception {
        // Given
        testReaderList();

        // When
        String responseJson = postman.perform(get("/reader"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].surname").value("Surname1"))
                .andExpect(jsonPath("$[0].name").value("Name1"))
                .andExpect(jsonPath("$[0].street").value("Street1"))
                .andExpect(jsonPath("$[0].streetNo").value("No1"))
                .andExpect(jsonPath("$[0].city").value("City1"))
                .andExpect(jsonPath("$[1].surname").value("Surname2"))
                .andExpect(jsonPath("$[1].name").value("Name2"))
                .andExpect(jsonPath("$[1].street").value("Street2"))
                .andExpect(jsonPath("$[1].streetNo").value("No2"))
                .andExpect(jsonPath("$[1].city").value("City2"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        List<ReaderDto> response = objectMapper.readValue(responseJson, new TypeReference<List<ReaderDto>>() {});
        List<Reader> readerListExpected = readerRepository.findAll();

        Assert.assertEquals(
                readerListExpected.stream()
                        .map(Reader::getSurname)
                        .toList(),
                response.stream()
                        .map(ReaderDto::getSurname)
                        .toList()
        );

    }

    public int testReader() {
        return readerRepository.saveAndFlush(new Reader(
                "ReaderSurname",
                "ReaderName",
                "Street",
                "City",
                "66"
        )).getId();
    }

    public List<Integer> testReaderList() {
        List<Reader> inputReaderList = new ArrayList<>(
                Arrays.asList(
                        new Reader("Surname1", "Name1", "Street1", "City1", "No1"),
                        new Reader("Surname2", "Name2", "Street2", "City2", "No2")
                )
        );

        readerRepository.saveAllAndFlush(inputReaderList);

        return inputReaderList.stream()
                .map(Reader::getId)
                .toList();
    }

}