package com.sabintarba.sabintarba.services;

import com.sabintarba.sabintarba.crypto.AES;
import com.sabintarba.sabintarba.models.User;
import com.sabintarba.sabintarba.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public String registerAccount(User user){

        try {
            userRepository.save(user);
            return new JSONObject().put("status", "success").put("statusMessage", "Your account has been successfully created!").toString();

        } catch(DuplicateKeyException emailIndexException){
            return new JSONObject().put("status", "fail").put("statusMessage", "Email " + user.getEmail() + " already exists!").toString();
        }
    }

    public String logIn(String email, String password) {

        Optional<User> userOptional = this.userRepository.findByEmailAndPassword(email, password);

        AES aes = new AES();

        if(userOptional.isPresent()){
            return new JSONObject().
                    put("status", "success").
                    put("token", aes.encrypt(userOptional.get().getId())).
                    put("encryptedData", aes.encrypt(userOptional.get().toJSONObject().toString())).
                    toString();
        } else {
            return new JSONObject().put("status", "fail").put("statusMessage", "User not found. Wrong credentials!").toString();
        }

    }

}
