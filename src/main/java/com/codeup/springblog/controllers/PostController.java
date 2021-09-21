package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")

    public String showPosts(Model model) {
        List<Post> listOfPosts = new ArrayList<>();
        Post p1 = new Post("Feeling funny", "today kinda sucks.");
        Post p2 = new Post("Feeling funny", "today kinda sucks. x2");

        listOfPosts.add(p1);
        listOfPosts.add(p2);
        model.addAttribute("listOfPosts", listOfPosts);
        return "/posts/post_index";
    }

    @GetMapping("/posts/{id}")

    public String showPostById(@PathVariable int id, Model model) {

        Post post = new Post("i find it kinda funny", "i find it kinda sad.");

        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getContent());

        return "/posts/view_post";
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
