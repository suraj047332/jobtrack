package com.jobtrack.service;
import com.jobtrack.repository.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class CustomUserDetailsService implements UserDetailsService {
 private final UserRepository users; public CustomUserDetailsService(UserRepository users){this.users=users;}
 @Override public UserDetails loadUserByUsername(String email){var user=users.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Account not found")); return User.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().name()).build();}
}
