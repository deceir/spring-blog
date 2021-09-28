package com.codeup.springblog.controllers;

import com.codeup.springblog.daos.UsersRepository;
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

    private final UsersRepository userDao;


    public PostController(PostsRepository postDao, UsersRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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
        model.addAttribute("post", post);

        return "/posts/view_post";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {

        model.addAttribute("post", new Post());

        return "/posts/create";
    }


    @PostMapping("/posts/create")
    public String createPostResponse(@ModelAttribute Post post) {

        post.setPoster(userDao.getById(1L));

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
