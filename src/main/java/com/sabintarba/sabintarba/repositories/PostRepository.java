package com.sabintarba.sabintarba.repositories;

import com.sabintarba.sabintarba.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByOrderByPostedAtDesc();

}
