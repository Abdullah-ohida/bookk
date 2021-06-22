package com.example.bookk.services;

import com.example.bookk.data.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    void findBookById() {

    }

    @Test
    void search() {
        List<Book> books = bookService.search("pride");
        log.info("List of book search --> {}", books);
    }

    @Test
    void getProgrammingBook() {
        List<Book> books = bookService.getProgrammingBooks();
        log.info("List of book search --> {}", books);
    }
}