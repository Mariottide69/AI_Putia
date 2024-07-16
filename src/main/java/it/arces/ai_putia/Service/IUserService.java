package it.arces.ai_putia.Service;


import java.util.List;
import java.util.Optional;

import it.arces.ai_putia.registration.RegistrationRequest;
import it.arces.ai_putia.registration.token.VerificationToken;

import it.arces.ai_putia.Model.User;

public interface IUserService {

   List<User> getUsers();

   User registerUser(RegistrationRequest request);

   Optional<User> findByEmail(String email);

   void saveUserVerificationToken(User theUser, String verificationToken);

   String validateToken(String token);

   VerificationToken generateNewVerificationToken(String oldToken);

}
