package com.example.bookk.services;

import com.example.bookk.data.model.Book;
import com.example.bookk.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    void findBookById() throws BookNotFoundException {
    Book book = bookService.findBookById("lfHo7uMk7r4C");
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("TCP/IP Sockets in Java");
    }

    @Test
    void search() {
        List<Book> books = bookService.search("pride");
        assertThat(books).isNotNull();
    }

    @Test
    void getProgrammingBook() {
        List<Book> books = bookService.getProgrammingBooks();
        assertThat(books).isNotNull();
    }
}