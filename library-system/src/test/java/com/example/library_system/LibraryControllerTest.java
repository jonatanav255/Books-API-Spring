package com.example.library_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(LibraryController.class)
@ActiveProfiles("test") // Activate the 'test' profile for this class
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        // Clear all books before each test
        mockMvc.perform(delete("/books/all")).andExpect(status().isOk());
    }

    @Test
    public void testGetBooksReturnsEmptyList() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testAddBook() throws Exception {
        String newBookJson = "{\"title\":\"1984\", \"author\":\"George Orwell\", \"year\":1949}";

        mockMvc.perform(post("/books")
                .contentType(APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("1984"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("George Orwell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(1949));
    }

    @Test
    public void testGetBooksAfterAddingBook() throws Exception {
        String newBookJson = "{\"title\":\"1984\", \"author\":\"George Orwell\", \"year\":1949}";

        // Add a book first
        mockMvc.perform(post("/books")
                .contentType(APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isOk());

        // Then check the list contains that book
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("1984"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("George Orwell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].year").value(1949));
    }
}
