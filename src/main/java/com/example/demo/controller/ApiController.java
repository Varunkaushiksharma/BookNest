package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Model.Student;
import com.example.demo.entities.User;
import com.example.demo.service.JWTService;
// import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ApiController {
  @Autowired
  AuthenticationManager authenticationManager;

  // @Autowired
  // private UserRepository userRepository;

  @Autowired
  private UserDetailService userDetailService;

  @Autowired
  private JWTService jwtService;

  List<Student> studentList = new ArrayList<>();

  public ApiController() {
    studentList.add(new Student(1, "varun", "varun@123"));
    studentList.add(new Student(2, "vansh", "vansh@123"));
    studentList.add(new Student(3, "kartik", "kartik@123"));
    studentList.add(new Student(4, "gaurav", "gaurav@123"));
  }

  @GetMapping("/hello")
  public String getMethodName() {
    return "hii new book is here";
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    return studentList;
  }

  @GetMapping("/students/{id}")
  public Student getStudentById(@PathVariable int id) {

    return studentList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);

  }

  @PutMapping("/{id}")
  public String updateStudent(@PathVariable int id, @RequestBody Student student) {
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).getId() == id) {
        studentList.set(i, student);
        return "Student updated successfully";
      }
    }
    return "Student not found";
  }

  @PostMapping("/students")
  public String addStudents(@RequestBody Student student) {
    studentList.add(student);
    return "Student added successfully";
  }

  // @PostMapping("/login-api")
  // public String login(@RequestBody User user) {
  // // Implement login logic here
  // User existingUser = userRepository.findByName(user.getName())
  // .orElseThrow(() -> new RuntimeException("User not found"));

  // if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword()))
  // {
  // return "Invalid username or password";
  // }

  // return "Login successful";
  // }

  @PostMapping("/loginapi")
  public String verifyUser(User user) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.genrateToken(user.getName());
    }
    return "Login failed";
  }

  @PostMapping("/signupapi")
  public User sigup(@RequestBody User user) {
    // Implement signup logic here
    return userDetailService.saveUser(user);
  }

  // @PostMapping("/signup-api")
  // public User signup(@RequestBody User user) {
  // // Implement signup logic here
  // user.setPassword(passwordEncoder.encode(user.getPassword()));
  // return userRepository.save(user);
  // }

}
