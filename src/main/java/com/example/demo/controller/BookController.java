package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.repositories.BookRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;

@RestController
@RequestMapping("/api")
public class BookController {

  private final BookRepository bookRepository;

  BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping("/books")
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @GetMapping("/books/{id}")
  public Book getBookById(@PathVariable int id) {
    Optional<Book> book = bookRepository.findById(id);
    return book.orElse(null);
  }

  @PostMapping("/books")
  public Book addBook(@RequestBody Book book) {
    bookRepository.save(book);
    return book;
  }

  @PutMapping("/books/{id}")
  public Book updateBook(@PathVariable int id, @RequestBody Book book) {
    Book bookUpdate = bookRepository.findById(id).orElse(null);
    if (bookUpdate != null) {
      bookUpdate.setName(book.getName());
      bookUpdate.setAuthor(book.getAuthor());
      bookUpdate.setSummary(book.getSummary());
      bookRepository.save(bookUpdate);
      return bookUpdate;
    }
    return null;
  }

  @DeleteMapping("/books/{id}")
  public String deleteBook(@PathVariable int id) {
    bookRepository.deleteById(id);
    return "Book deleted successfully";
  }

  @GetMapping("/books/search/{name}")
  public List<Book> findBookByName(@PathVariable String name) {
    return bookRepository.findAll().stream().filter(e -> e.getName().equalsIgnoreCase(name)).toList();
  }

}
