package it.arces.ai_putia.Controller;

import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import it.arces.ai_putia.Model.User;
import it.arces.ai_putia.Service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;

   @GetMapping()
   public List<User> getUsers() {
      return userService.getUsers();
   }

   // @GetMapping("/all")
   // public ResponseEntity<List<UserRecord>> getAllUsers() {
   // return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
   // }

   // @PostMapping("/add")
   // public ResponseEntity<User> add(@RequestBody User user) {
   // return ResponseEntity.ok(userService.add(user));
   // }

   // @GetMapping("/{email}")
   // public User getByEmail(@PathVariable("email") String email) {
   // return userService.getUser(email);
   // }

   // @DeleteMapping("/{email}")
   // public void delete(@PathVariable("email") String email) {
   // userService.delete(email);
   // }

   // @PutMapping("/update")
   // public ResponseEntity<User> update(@RequestBody User user) {
   // return ResponseEntity.ok(userService.update(user));
   // }

}
