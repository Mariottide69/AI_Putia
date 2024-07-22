package it.arces.ai_putia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.arces.ai_putia.security.jwt.JWTAuthenticationFilter;

// import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class ShopSecurityConfig {

   // private static final String[] SECURED_URLs = { 

   //      };

   private static final String[] UN_SECURED_URLs = {
         "/users/**",
         "/authenticate/**",
         "/register/**",
         "/login.html",
         "/products.html",
         "/api/auth/logout"
   };

   @Autowired
   private JWTAuthenticationFilter authenticationFilter;

   @Autowired
   private ShopUserDetailsService userDetailsService;

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }

   
   @SuppressWarnings("removal")
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
      //   .csrf().disable()
      //           .authorizeHttpRequests()
      //           .requestMatchers(UN_SECURED_URLs).permitAll().and()
      //           .authorizeHttpRequests().requestMatchers(SECURED_URLs)
      //           .hasAuthority("ADMIN").anyRequest().authenticated()
      //           .and().sessionManagement()
      //           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      //           .and()
      //           .authenticationProvider(authenticationProvider())
      //           .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
      //           .build();
    }


   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
      return authConfig.getAuthenticationManager();
   }
}
