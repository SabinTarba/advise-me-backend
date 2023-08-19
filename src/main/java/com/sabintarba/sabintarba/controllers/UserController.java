package com.sabintarba.sabintarba.controllers;


import com.sabintarba.sabintarba.crypto.AES;
import com.sabintarba.sabintarba.models.User;
import com.sabintarba.sabintarba.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private AES aes;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestHeader("token") String token){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<User> response = this.userService.getUsers();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@RequestHeader("token") String token, @PathVariable String id){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.userService.getUserById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/email/{email}")
    public ResponseEntity<String> getUserByEmail(@RequestHeader("token") String token, @PathVariable String email){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.userService.getUserByEmail(email);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@RequestHeader("token") String token, @PathVariable String id){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.userService.deleteUserById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestHeader("token") String token, @RequestBody User user){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.userService.updateUser(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/removeProfilePicture")
    public ResponseEntity<String> removeProfilePicture(@RequestHeader("token") String token){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.userService.removeProfilePicture(aes.getIdFromToken(token));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
