package com.slightlytechie.blogAPI.dto;

import com.slightlytechie.blogAPI.model.BlogPost;
import lombok.Data;

import java.util.List;

@Data
public class BlogPostResponse extends MessageResponse{

    private BlogPost blogPost;

    private List<BlogPost> blogPosts;
}
