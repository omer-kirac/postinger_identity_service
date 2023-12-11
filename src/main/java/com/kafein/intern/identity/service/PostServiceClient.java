package com.kafein.intern.identity.service;

import com.kafein.intern.identity.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="post-service",url = "https://localhost:8080")
public interface PostServiceClient {
    @GetMapping(value = "/posts/user-posts/{userId}")
    public List<Post> getPosts(@RequestParam Long userId);

    @PostMapping(value = "/posts/create-post")
    public Post createPost(@RequestBody Post post);

    @PutMapping(value = "/posts/update-post")
    public Post updatePost(@RequestBody Post post);

    @DeleteMapping(value = "posts/delete-post/{postId}")
    public String  deletePost(@RequestParam String postId);


}


