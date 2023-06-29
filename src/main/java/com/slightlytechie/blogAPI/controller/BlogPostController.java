package com.slightlytechie.blogAPI.controller;

import com.slightlytechie.blogAPI.dto.BlogPostResponse;
import com.slightlytechie.blogAPI.model.BlogPost;
import com.slightlytechie.blogAPI.service.BlogPostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/api/v1/blogPost")
public class BlogPostController {



   private final BlogPostService blogPostService;

    @PostMapping(path = "createBlogPost")
    public BlogPostResponse createPost(@RequestBody BlogPost blogPost){
        log.info("Received request to create blog post...");
        return blogPostService.createPost(blogPost);
    }

    @GetMapping(value = "viewOneBlogPost/{blogPostId}")
    public BlogPostResponse viewOneBlogPost(@PathVariable("blogPostId")  Long blogPostId){
        log.info("Received request to get a blog post...");
        return blogPostService.viewOneBlogPost(blogPostId);
    }


    @GetMapping(path = "viewAllBlogPosts")
    public BlogPostResponse viewAllBlogPosts(){
        log.info("Received request to get a blog post...");
        return blogPostService.viewAllBlogPosts();
    }

    @PostMapping(path = "updateBlogPost")
    public BlogPostResponse updateBlogPost(@RequestBody BlogPost blogPost){
        log.info("Received request to update blog post...");
        return blogPostService.updateBlogPost(blogPost);
    }

    @PostMapping(path = "deleteBlogPost")
    public BlogPostResponse deleteBlogPost(@RequestBody BlogPost blogPost){
        log.info("Received request to update delete post...");
        return blogPostService.deleteBlogPost(blogPost);
    }



}
