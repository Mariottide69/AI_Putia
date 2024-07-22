package it.arces.ai_putia.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
// import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JWTService {

   @Value("${spring.jwt.secret}")
   private String JWT_SECRET;

   @Value("${spring.jwt.jwtExpirationInMs}")
   private int JWT_EXPIRATION_TIME_IN_MILLISECONDS;

   private final Map<String, Date> invalidatedTokens = new ConcurrentHashMap<>();

   public String generateToken(String userName) {
      Map<String, Object> claims = new HashMap<>();
      return tokenCreator(claims, userName);
   }

   public String tokenCreator(Map<String, Object> claims, String userName) {
      return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME_IN_MILLISECONDS))
            .signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
   }

   public void invalidateToken(String token) {
      Claims claims = extractAllClaims(token);
      Date expirationDate = claims.getExpiration();
      invalidatedTokens.put(token, expirationDate);
   }


   private Key getSignedKey() {
      byte[] keyByte = Decoders.BASE64.decode(JWT_SECRET);
      return Keys.hmacShaKeyFor(keyByte);
   }

   public String extractUsernameFromToken(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public Date extractExpirationTimeFromToken(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      final String userName = extractUsernameFromToken(token);
      return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenInvalidated(token));
   }

   private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      return Jwts.parserBuilder()
            .setSigningKey(getSignedKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

   }

   private boolean isTokenInvalidated(String token) {
      return invalidatedTokens.containsKey(token) && !isTokenExpired(token);
   }

   private boolean isTokenExpired(String token) {
      Date expiration = extractExpirationTimeFromToken(token);
      return expiration.before(new Date());
   }

   public void cleanupInvalidatedTokens() {
      Date now = new Date();
      invalidatedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
   }

}