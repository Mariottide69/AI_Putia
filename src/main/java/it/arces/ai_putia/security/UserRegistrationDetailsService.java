package it.arces.ai_putia.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.arces.ai_putia.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

      return userRepository.findByEmail(email)
      .map(UserRegistrationDetails::new)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
   }
}
