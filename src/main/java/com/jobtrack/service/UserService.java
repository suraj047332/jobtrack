package com.jobtrack.service;
import com.jobtrack.model.*; import com.jobtrack.repository.UserRepository; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service public class UserService {
 private final UserRepository users; private final PasswordEncoder encoder;
 public UserService(UserRepository users,PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
 public AppUser register(String name,String email,String password){String normalized=email.trim().toLowerCase(); if(users.existsByEmail(normalized)) throw new IllegalArgumentException("An account with this email already exists."); AppUser u=new AppUser();u.setFullName(name.trim());u.setEmail(normalized);u.setPassword(encoder.encode(password));u.setRole(Role.USER);return users.save(u);}
 public AppUser byEmail(String email){return users.findByEmail(email).orElseThrow();}
 public void updateProfile(String email,String name){AppUser u=byEmail(email);u.setFullName(name.trim());users.save(u);}
 public void changePassword(String email,String current,String next){AppUser u=byEmail(email);if(!encoder.matches(current,u.getPassword()))throw new IllegalArgumentException("Current password is incorrect.");u.setPassword(encoder.encode(next));users.save(u);}
}
