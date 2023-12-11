package com.kafein.intern.identity.service;

import com.kafein.intern.identity.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="comment-service",url = "https://localhost:8081")
public interface CommentServiceClient {
    @GetMapping(value = "/comment/find-post-comment/{postId}")
    public List<Post> findCommentPostById(@RequestParam String postId);




}
