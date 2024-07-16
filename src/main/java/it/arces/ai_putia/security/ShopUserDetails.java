package it.arces.ai_putia.security;

import it.arces.ai_putia.Model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopUserDetails implements UserDetails {

   private String userName;
   private String password;
   private List<GrantedAuthority> authorities;

   public ShopUserDetails(User user) {
      userName = user.getEmail();
      password = user.getPassword();
      authorities = Arrays.stream(user.getRole()
            .split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return authorities;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return userName;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
