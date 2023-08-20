package com.sabintarba.sabintarba.services;

import com.sabintarba.sabintarba.models.Post;
import com.sabintarba.sabintarba.models.User;
import com.sabintarba.sabintarba.repositories.PostRepository;
import com.sabintarba.sabintarba.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public String createPost(Post post, String userId){

        post.setPostedAt(new Date());

        Optional<User> postedByUser = this.userRepository.findById(userId);

        if(postedByUser.isPresent()){
            post.setPostedBy(postedByUser.get());
            Post savedPost = postRepository.save(post);
            return new JSONObject().put("status", "success").put("id", savedPost.getId()).put("statusMessage", "Post created and pulished!").toString();

        } else {
            return new JSONObject().put("status", "fail").put("statusMessage", "Post wans't published. Error occured!").toString();
        }
    }

    public String getPostById(String id) {
        Optional<Post> postOptional = this.postRepository.findById(id);

        if(postOptional.isPresent()) {
            return new JSONObject().put("status", "success").put("post", postOptional.get().toJSONObject()).toString();

        } else {
            return new JSONObject().put("status", "fail").put("statusMessage", "User not found!").toString();
        }
    }

    public List<Post> getPosts() {
        return this.postRepository.findAllByOrderByPostedAtDesc();
    }

    public String deletePostById(String id) {
        this.postRepository.deleteById(id);

        return new JSONObject().put("status", "success").put("statusMessage", "Post has been deleted!").toString();
    }
}
