package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.daos.PostsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostsRepository postDao;

    public PostController(PostsRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")

    public String showPosts(Model model) {
        List<Post> listOfPosts = postDao.findAll();
        System.out.println(listOfPosts);

        model.addAttribute("listOfPosts", listOfPosts);
        return "/posts/post_index";
    }

    @GetMapping("/posts/{id}")

    public String showPostById(@PathVariable long id, Model model) {

        Post post = postDao.getById(id);
        model.addAttribute("id", post.getId());
        model.addAttribute("title", post.getTitle());
        model.addAttribute("content", post.getDescription());

        return "/posts/view_post";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "/posts/create";
    }


    @PostMapping("/posts/create")
    public String createPostResponse(@RequestParam String title, @RequestParam String description) {
        Post post = new Post(title, description);

        postDao.save(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable long id, @RequestParam String title, @RequestParam String description) {


        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setDescription(description);


        postDao.save(post);

        return "redirect:/posts";

    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {

        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "/posts/delete";

    }

    @PostMapping("/posts/delete/{id}")
    public String deletePostForm(@PathVariable long id, @RequestParam int delete) {


        if (delete == 1) {
            postDao.deleteById(id);
        }

        return "redirect:/posts";

    }

}
