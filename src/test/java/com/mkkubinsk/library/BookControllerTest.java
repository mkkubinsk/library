package com.mkkubinsk.library;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkkubinsk.library.model.Book;
import com.mkkubinsk.library.model.command.CreateBookCommand;
import com.mkkubinsk.library.model.dto.BookDto;
import com.mkkubinsk.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
        //given
        CreateBookCommand command = new CreateBookCommand();
        command.setAuthor("testAuthor");
        command.setTitle("testTitle");
        command.setReleaseYear(LocalDate.of(2020,5,7));

        //when
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

        //then
        BookDto response = objectMapper.readValue(responseJson, BookDto.class);
        Book bookExpected = bookRepository.findById(response.getId()).get();

        Assertions.assertEquals(response.getAuthor(), bookExpected.getAuthor());

                //                .andExpect(jsonPath("$[1].brand").value("opel"))
        //                .andExpect(jsonPath("$[1].id").value(carID2))
        //                .andExpect(jsonPath("$[1].fuel").value("ON"))
        //                .andExpect(jsonPath("$[1].width").value(200));


    }
//
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


    public int testBook() {
        return bookRepository.saveAndFlush(new Book("test123", "testowy author", LocalDate.of(2020, 7, 8))).getId();
    }


}
