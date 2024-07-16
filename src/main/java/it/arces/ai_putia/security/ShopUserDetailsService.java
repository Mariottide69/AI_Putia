package it.arces.ai_putia.security;

import it.arces.ai_putia.Repository.UserRepository;
// import it.arces.ai_putia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class ShopUserDetailsService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepository.findByEmail(username)
            .map(ShopUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("No user found"));
   }
}
