package com.example.bookk.services;

import com.example.bookk.data.model.Book;
import com.example.bookk.web.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    Book findBookById(String id) throws BookNotFoundException;
    List<Book> search(String bookTitle);
    List<Book> getProgrammingBooks();
}
