package com.hospital.management.controllers;


import com.hospital.management.entities.User;
import com.hospital.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@RestController
/*@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;
    private JwtGeneratorInterface jwtGenerator;
    @Autowired
    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
    }
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUserName() == null || user.getPassword() == null) {
                throw new UsernameNotFoundException("UserName or Password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUserName(), user.getPassword());
            if(userData == null){
                throw new UsernameNotFoundException("UserName or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}*/

@RestController
@RequestMapping("/sign-in")
public class UserController {

   @Autowired
   private UserService userService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User userCreation = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreation);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserbyUserId(@PathVariable String userId){
       User user= userService.getUser(userId);
      return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
       List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
   }

}
