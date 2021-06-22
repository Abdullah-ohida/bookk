package com.example.bookk.web.controllers;

import com.example.bookk.data.model.Book;
import com.example.bookk.services.BookService;
import com.example.bookk.web.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class bookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public String index(Model model){
        List<Book> books = bookService.getProgrammingBooks();
        log.info("Result --> {}", books);
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("book/{id}")
    public String findBookById(@PathVariable("id") String id, Model model)  {
        try {
            Book book = bookService.findBookById(id);
            model.addAttribute("book", book);
            return "book";
        }catch (BookNotFoundException e){
            return "redirect:/";
        }

    }

    @GetMapping("book/all")
    public String search(@RequestParam(name = "title") String name, Model model){
        List<Book> books = bookService.search(name);
        model.addAttribute("books", books);
        return "books";
    }
}
