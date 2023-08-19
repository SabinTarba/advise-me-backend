package com.sabintarba.sabintarba.crypto;

import com.sabintarba.sabintarba.repositories.UserRepository;
import com.sabintarba.sabintarba.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


@Service
public class AES {

    private final String key = "aesEncryptionKey";
    private final String initVector = "encryptionIntVec";
    @Autowired
    private UserRepository userRepository;

    public String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            System.out.println("[TOKEN ERROR] Token error for sending response!");
        }
        return null;
    }
    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            System.out.println("[TOKEN ERROR] Token error for request => request denied!");
        }

        return null;
    }

    public boolean isAuthorizedRequest(String token){

        try{
            if(token == null) return false;

            String decryptedToken = decrypt(token);

            if(decryptedToken == null) return false;

            return userRepository.findById(decryptedToken).isPresent();

        } catch (Exception ex){
            System.out.println("[TOKEN ERROR] Token error for request => request denied!");
            return false;
        }
    }

    public String getIdFromToken(String token){
        try{
            if(token == null) return null;

            return decrypt(token);

        } catch (Exception ex){
            return null;
        }
    }
}
