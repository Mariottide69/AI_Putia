package it.arces.ai_putia.exception;

public class UserAlreadyExistsException extends RuntimeException {
   public UserAlreadyExistsException(String message) {
      super(message);
   }
}
