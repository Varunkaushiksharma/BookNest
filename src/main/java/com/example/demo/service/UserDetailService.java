package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    if (user == null) {
      throw new UsernameNotFoundException("User not found with the name: " + username);
    }

    return new org.springframework.security.core.userdetails.User(
        user.getName(),
        user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }

  public User saveUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

}
