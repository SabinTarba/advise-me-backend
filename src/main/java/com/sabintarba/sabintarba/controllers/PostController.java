package com.sabintarba.sabintarba.controllers;

import com.sabintarba.sabintarba.crypto.AES;
import com.sabintarba.sabintarba.models.Post;
import com.sabintarba.sabintarba.services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private AES aes;
    @Autowired
    private PostService postService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestHeader("token") String token){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<Post> response = this.postService.getPosts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> createPost(@RequestHeader("token") String token, @RequestBody Post post){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.postService.createPost(post, aes.decrypt(token));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<String> getPostById(@RequestHeader("token") String token, @PathVariable String id){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.postService.getPostById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@RequestHeader("token") String token, @PathVariable String id){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        String response = this.postService.deletePostById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@RequestHeader("token") String token, @PathVariable String userId){
        if(!aes.isAuthorizedRequest(token)) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

        List<Post> response = this.postService.getPostsByUserId(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
