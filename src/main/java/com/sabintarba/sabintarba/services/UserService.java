package com.sabintarba.sabintarba.services;


import com.sabintarba.sabintarba.models.User;
import com.sabintarba.sabintarba.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
    public String saveUser(User user){

        try {
            this.userRepository.save(user);
            return new JSONObject().put("status", "success").put("statusMessage", "Your account has been successfully created!").toString();

        } catch(DuplicateKeyException emailIndexException){
            return new JSONObject().put("status", "fail").put("statusMessage", "Email " + user.getEmail() + " already exists!").toString();
        }
    }

    public String getUserById(String id) {
        Optional<User> userOptional = this.userRepository.findById(id);

        if(userOptional.isPresent()) {
            return new JSONObject().put("status", "success").put("user", userOptional.get().toJSONObject()).toString();

        } else {
            return new JSONObject().put("status", "fail").put("statusMessage", "User not found!").toString();
        }
    }

    public String getUserByEmail(String email) {
        Optional<User> userOptional = this.userRepository.findByEmail(email);

        if(userOptional.isPresent()) {
            return new JSONObject().put("status", "success").put("user", userOptional.get().toJSONObject()).toString();

        } else {
            return new JSONObject().put("status", "fail").put("errorMessage", "User not found!").toString();
        }
    }

    public String deleteUserById(String id) {
        this.userRepository.deleteById(id);

        return new JSONObject().put("status", "success").toString();
    }

    public String updateUser(User user){
        try {
            User savedUser = this.userRepository.save(user);
            return new JSONObject().put("status", "success").put("savedUser", savedUser.toJSONObject()).put("statusMessage", "Profile has been updated!").toString();

        } catch(DuplicateKeyException emailIndexException){
            return new JSONObject().put("status", "fail").put("statusMessage", "Email " + user.getEmail() + " already exists!").toString();
        }
    }

    public String removeProfilePicture(String id) {

        Optional<User> userFound = this.userRepository.findById(id);

        if(userFound.isEmpty()){
            return new JSONObject().put("status", "fail").put("statusMessage", "Something went wrong!").toString();
        }

        User user = userFound.get();
        user.setProfilePicture(null);

        this.userRepository.save(user);

        return new JSONObject().put("status", "success").put("statusMessage", "Profile picture has been removed!").toString();
    }
}
