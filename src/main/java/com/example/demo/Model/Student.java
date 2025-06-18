package com.example.demo.Model;

public class Student {

  int id;
  String name;
  String email;

  Student() {
  }

  // constructor
  public Student(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;

  }

  // getter and setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNAme() {
    return name;
  }

  public void setNAme(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
