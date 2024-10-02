package com.example.library_system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookCreation() {
        Book book = new Book("1984", "George Orwell", 1949);
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals(1949, book.getYear());
    }

    @Test
    void testSetters() {
        Book book = new Book();
        book.setTitle("Animal Farm");
        book.setAuthor("George Orwell");
        book.setYear(1945);

        assertEquals("Animal Farm", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals(1945, book.getYear());
    }
}
