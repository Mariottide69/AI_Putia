package it.arces.ai_putia.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
   private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

   @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
      logger.error("Errore gestito: ", ex);
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(UserAlreadyExistsException.class)
   public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
      logger.error("Errore gestito: ", ex);
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }
}
