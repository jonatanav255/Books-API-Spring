package com.example.library_system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Allow access from any origin
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

    // Delete all books (only in test profile)
    @DeleteMapping("/books/all")
    @Profile("test") // Ensures this endpoint is only available in the 'test' profile
    public void deleteAllBooks() {
        books.clear();
    }
}
