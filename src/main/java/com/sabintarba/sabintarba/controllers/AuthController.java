package com.sabintarba.sabintarba.controllers;

import com.sabintarba.sabintarba.models.User;
import com.sabintarba.sabintarba.services.AuthService;
import org.apache.catalina.connector.Request;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody User user){
        String response = this.authService.registerAccount(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> logIn (@RequestBody String request){
        JSONObject object = new JSONObject(request); // "email" and "password"

        String response = this.authService.logIn(object.getString("email"), object.getString("password"));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
