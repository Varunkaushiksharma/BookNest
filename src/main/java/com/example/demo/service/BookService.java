package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

// import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  // to get all books
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  // to find book by id
  public Book getBookById(int id) {
    Optional<Book> book = bookRepository.findById(id);
    return book.orElse(null);
  }

  // to add book
  public Book addBook(Book book) {
    bookRepository.save(book);
    return book;
  }

  // to update the book
  public Book updateBook(int id, Book book) {
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

  // to delete the book
  public String deleteBook(int id) {
    bookRepository.deleteById(id);
    return "Book deleted successfully";
  }

  // to find the book by name
  public List<Book> findBookByName(String name) {
    return bookRepository.findAll().stream().filter(e -> e.getName().equalsIgnoreCase(name)).toList();
  }

}
