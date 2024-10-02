package com.example.library_system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    private List<Book> books = new ArrayList<>();

    // Get all books
    @GetMapping("/books")
    public List<Book> getBooks() {
        return books;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
}
