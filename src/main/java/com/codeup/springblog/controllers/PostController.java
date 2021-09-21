package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String showPosts() {
        return "Viewing all posts.";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPostById(@PathVariable int id) {
        return "Viewing post with id of: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "Showing form to create post.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPostResponse() {
        return "Your post has been created.";
    }

}
