package it.arces.ai_putia.security.jwt;

import it.arces.ai_putia.security.jwt.JWTService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

   private final JWTService jwtService;

   public SchedulerConfig(JWTService jwtService) {
      this.jwtService = jwtService;
   }

   @Scheduled(fixedRate = 3600000) // Esegui ogni ora
   public void cleanupInvalidatedTokens() {
      jwtService.cleanupInvalidatedTokens();
   }
}
