package it.arces.ai_putia.Service;

import it.arces.ai_putia.exception.UserAlreadyExistsException;
// import it.arces.ai_putia.exception.UserAlreadyExistsException;
// import it.arces.ai_putia.exception.UserNotFoundException;
import it.arces.ai_putia.registration.RegistrationRequest;
import it.arces.ai_putia.registration.token.VerificationToken;
import it.arces.ai_putia.registration.token.VerificationTokenRepository;
import it.arces.ai_putia.Model.User;
import it.arces.ai_putia.Repository.UserRepository;
// import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// import org.slf4j.Logger;

// import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collectors;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final VerificationTokenRepository tokenRepository;

   @Override
   public List<User> getUsers() {
      return userRepository.findAll();
   }

   
   public User registerUser(RegistrationRequest request) {
      Optional<User> user = this.findByEmail(request.email());
      if (user.isPresent()) {
         throw new UserAlreadyExistsException(
               "User with email " + request.email() + " already exists");
      }
      var newUser = new User();
      newUser.setFirstName(request.firstName());
      newUser.setLastName(request.lastName());
      newUser.setUserName(request.userName());
      newUser.setEmail(request.email());
      newUser.setPassword(passwordEncoder.encode(request.password()));
      newUser.setPhonenumber(request.phonenumber());
      newUser.setRole(request.role());
      return userRepository.save(newUser);
   }

   @Override
   public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
   }

   @Override
   public void saveUserVerificationToken(User theUser, String token) {
      var verificationToken = new VerificationToken(token, theUser);
      tokenRepository.save(verificationToken);
   }

   @Override
   public String validateToken(String theToken) {
      VerificationToken token = tokenRepository.findByToken(theToken);
      if (token == null) {
         return "Invalid verification token";
      }
      User user = token.getUser();
      Calendar calendar = Calendar.getInstance();
      if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
         return "Verification link already expired," +
               " Please, click the link below to receive a new verification link";
      }
      user.setEnabled(true);
      userRepository.save(user);
      return "valid";
   }

   @Override
   public VerificationToken generateNewVerificationToken(String oldToken) {
      VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
      var tokenExpirationTime = new VerificationToken();
      verificationToken.setToken(UUID.randomUUID().toString());
      verificationToken.setExpirationTime(tokenExpirationTime.getTokenExpirationTime());
      return tokenRepository.save(verificationToken);
   }

   // private final UserRepository userRepository;
   // private final PasswordEncoder passwordEncoder;

   // @Override
   // public User add(User user) {
   // Optional<User> theUser = userRepository.findByEmail(user.getEmail());
   // if (theUser.isPresent()) {
   // throw new UserAlreadyExistsException("A user with " + user.getEmail() + "
   // already exists");
   // }
   // user.setPassword(passwordEncoder.encode(user.getPassword()));
   // return userRepository.save(user);
   // }

   // @Override
   // public List<UserRecord> getAllUsers() {
   // return userRepository.findAll()
   // .stream()
   // .map(user -> new UserRecord(
   // user.getId(),
   // user.getFirstName(),
   // user.getLastName(),
   // user.getEmail()))
   // .collect(Collectors.toList());
   // }

   // @Override
   // @Transactional
   // public void delete(String email) {
   // userRepository.deleteByEmail(email);
   // }

   // @Override
   // public User getUser(String email) {
   // return userRepository.findByEmail(email)
   // .orElseThrow(() -> new UserNotFoundException("User not found"));
   // }

   // @Override
   // public User update(User user) {
   // user.setRoles(user.getRoles());
   // return userRepository.save(user);
   // }
}
