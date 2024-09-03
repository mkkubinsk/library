package com.mkkubinsk.library;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.dto.BookDto;
import com.mkkubinsk.library.repository.BookRepository;
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
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LibraryApplication.class})
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc postman;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //https://www.baeldung.com/integration-testing-in-spring

    @Test
    void shouldCreateSingleBook() throws Exception{
        // Given
        CreateBookCommand command = new CreateBookCommand();
        command.setAuthor("testAuthor");
        command.setTitle("testTitle");
        command.setReleaseYear(LocalDate.of(2020,5,7));

        // When
        String responseJson = postman.perform(post("/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("testTitle"))
                .andExpect(jsonPath("$.author").value("testAuthor"))
                .andExpect(jsonPath("$.releaseYear").value("2020-05-07"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        BookDto response = objectMapper.readValue(responseJson, BookDto.class);
        Book bookExpected = bookRepository.findById(response.getId()).get();

        Assertions.assertEquals(response.getAuthor(), bookExpected.getAuthor());
    }

//    @BeforeEach
//    void clean(){
//        bookRepository.deleteAll();
//    }

    @Test
    void shouldGetSingleBook() throws Exception {
        int testBookId = testBook();

        postman.perform(get("/book/" + testBookId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("test123"));
    }

    @Test
    void shouldGetAllBooks() throws Exception {

        // Given
        testBookList();

        // When
        String responseJson = postman.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].author").value("author1"))
                .andExpect(jsonPath("$[0].releaseYear").value("2021-01-01"))
                .andExpect(jsonPath("$[1].title").value("title2"))
                .andExpect(jsonPath("$[1].author").value("author2"))
                .andExpect(jsonPath("$[1].releaseYear").value("2022-01-01"))
                .andExpect(jsonPath("$[2].title").value("title3"))
                .andExpect(jsonPath("$[2].author").value("author3"))
                .andExpect(jsonPath("$[2].releaseYear").value("2023-01-01"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Then
        List<BookDto> response = objectMapper.readValue(responseJson, new TypeReference<List<BookDto>>(){});
        List<Book> bookListExpected = bookRepository.findAll();

        Assert.assertEquals(
                bookListExpected.stream()
                        .map(Book::getTitle)
                        .toList(),
                response.stream()
                        .map(BookDto::getTitle)
                        .toList()
        );
    }


    public int testBook() {
        return bookRepository.saveAndFlush(new Book("test123", "testowy author", LocalDate.of(2020, 7, 8))).getId();
    }

    public List<Integer> testBookList() {
        List<Book> inputBookList = new ArrayList<>(
                Arrays.asList(
                        new Book("title1", "author1", LocalDate.of(2021, 1, 1)),
                        new Book("title2", "author2", LocalDate.of(2022, 1, 1)),
                        new Book("title3", "author3", LocalDate.of(2023, 1, 1))
                )
        );

        bookRepository.saveAllAndFlush(inputBookList);

        return inputBookList.stream()
                .map(Book::getId)
                .toList();
    }


}
