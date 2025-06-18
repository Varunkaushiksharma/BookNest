package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.service.BookService;

import org.springframework.ui.Model;

@Controller
public class Home {

  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private BookService bookService;

  // Home page – show only a few books (4)
  @GetMapping("/home")
  public String showHomePage(Model model) {
    List<Book> books = bookRepository.findAll().stream()
        .limit(4)
        .toList();
    model.addAttribute("books", books);
    return "home";
  }

  @GetMapping("/book")
  public String books(Model model) {

    List<Book> books = bookRepository.findAll();
    model.addAttribute("books", books);
    return "book";

  }

  @GetMapping("/books/read/{id}")
  public String readBook(@PathVariable int id, Model model) {
    Book book = bookService.getBookById(id); // Fetch book from DB
    model.addAttribute("book", book); // Send book data to template
    return "read"; // Loads read.html
  }

  @GetMapping("/account")
  public String accountPage(Model model, Principal principal) {
    model.addAttribute("username", principal.getName());
    return "account"; // account.html in templates/
  }

  @GetMapping("/library")
  public String library() {
    return "library";
  }

}
