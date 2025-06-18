package com.example.demo.controller;

import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/signup")
  public String signup() {
    return "signup";
  }

  @PostMapping("/signup")
  public String signUp(@ModelAttribute User user) {

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);

    return "redirect:/login";

  }

  // @PostMapping("/login")
  // public String login(@ModelAttribute User user) {
  // // This method is not used as Spring Security handles the login process

  // return "redirect:/home";
  // }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

}
