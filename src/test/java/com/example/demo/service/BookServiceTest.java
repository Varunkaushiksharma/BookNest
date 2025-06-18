package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @InjectMocks
  private BookService bookService;

  private Book sampleBook;

  @BeforeEach
  void setUp() {
    sampleBook = new Book();
    sampleBook.setId(1);
    sampleBook.setName("Java Basics");
    sampleBook.setAuthor("James Gosling");
    sampleBook.setSummary("Intro to Java");
  }

  // @Test
  // public void testGetAllBooks() {

  // Book book1 = bookService.getBookById(1);
  // assertNotNull(book1);

  // }

  @Test
  void testGetAllBooks() {
    when(bookRepository.findAll()).thenReturn(List.of(sampleBook));
    List<Book> books = bookService.getAllBooks();
    assertEquals(1, books.size());
    assertEquals("Java Basics", books.get(0).getName());
  }

  @Test
  void testGetBookById_Found() {
    when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook));
    Book book = bookService.getBookById(1);
    assertEquals("Java Basics", book.getName());
  }

  @Test
  void testGetBookById_NotFound() {
    when(bookRepository.findById(2)).thenReturn(Optional.empty());
    Book book = bookService.getBookById(2);
    assertEquals(null, book);
  }

  @Test
  void testAddBook() {
    when(bookRepository.save(sampleBook)).thenReturn(sampleBook);
    Book addedBook = bookService.addBook(sampleBook);
    assertEquals("Java Basics", addedBook.getName());
  }

  @Test
  void testUpdateBook_Found() {
    Book updatedInfo = new Book();
    updatedInfo.setName("Advanced Java");
    updatedInfo.setAuthor("John Doe");
    updatedInfo.setSummary("Advanced Java concepts");

    when(bookRepository.findById(1)).thenReturn(Optional.of(sampleBook));
    when(bookRepository.save(sampleBook)).thenReturn(sampleBook);

    Book updatedBook = bookService.updateBook(1, updatedInfo);
    assertEquals("Advanced Java", updatedBook.getName());
    assertEquals("John Doe", updatedBook.getAuthor());
    assertEquals("Advanced Java concepts", updatedBook.getSummary());
  }

  @Test
  void testUpdateBook_NotFound() {
    Book updatedInfo = new Book();
    when(bookRepository.findById(2)).thenReturn(Optional.empty());
    Book updatedBook = bookService.updateBook(2, updatedInfo);
    assertEquals(null, updatedBook);
  }

  @Test
  void testDeleteBook() {
    // We don't have a return from repository.deleteById, so just call and check
    // message
    String response = bookService.deleteBook(1);
    assertEquals("Book deleted successfully", response);
  }

  @Test
  void testFindBookByName() {
    Book anotherBook = new Book();
    anotherBook.setName("Java Basics");
    anotherBook.setAuthor("Someone Else");
    anotherBook.setSummary("Another book summary");

    List<Book> booksList = List.of(sampleBook, anotherBook);

    when(bookRepository.findAll()).thenReturn(booksList);

    List<Book> result = bookService.findBookByName("java basics");
    assertEquals(2, result.size()); // Both books have name "Java Basics" ignoring case
  }

}
