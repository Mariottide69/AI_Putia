package it.arces.ai_putia.security.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JWTAuthenticationRequest {
   private String userName;
   private String password;
}
