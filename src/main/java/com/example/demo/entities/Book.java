package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String author;
  private String summary;
  // private String pdfUrl;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Book() {
  }

  // constructor
  public Book(String name, String author, String summary) {
    this.name = name;
    this.author = author;
    this.summary = summary;
    // this.pdfUrl = pdfUrl;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  // getter and setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  // public String getPdfUrl() {
  // return pdfUrl;
  // }

  // public void setPdfUrl(String pdfUrl) {
  // this.pdfUrl = pdfUrl;
  // }

  @Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", author=" + author + ", summary=" + summary
        + "]";
  }

}
