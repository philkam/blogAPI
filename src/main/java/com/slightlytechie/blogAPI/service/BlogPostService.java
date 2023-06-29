package com.slightlytechie.blogAPI.service;

import com.slightlytechie.blogAPI.dto.BlogPostResponse;
import com.slightlytechie.blogAPI.model.BlogPost;
import com.slightlytechie.blogAPI.model.BlogPostStatus;
import com.slightlytechie.blogAPI.repository.BlogPostRepository;
import com.slightlytechie.blogAPI.util.Settings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    //Save blog post
    public void savePost(BlogPost blogPost) {
        BlogPost blogPostToSave = new BlogPost();
        try {
            blogPostToSave.setBlogPostAuthor(blogPost.getBlogPostAuthor());
            blogPostToSave.setBlogPostContent(blogPost.getBlogPostContent());
            blogPostToSave.setBlogPostImageURL(blogPost.getBlogPostImageURL());
            blogPostToSave.setBlogPostDeletedAt(blogPost.getBlogPostDeletedAt());
            blogPostToSave.setBlogPostCreatedAt(blogPost.getBlogPostCreatedAt());
            blogPostToSave.setBlogPostCreatedBy(blogPost.getBlogPostCreatedBy());
            blogPostToSave.setBlogPostDeletedBy(blogPost.getBlogPostDeletedBy());
            blogPostToSave.setBlogPostTag(blogPost.getBlogPostTag());
            blogPostToSave.setBlogPostStatus(blogPost.getBlogPostStatus());
            blogPostToSave.setBlogPostTitle(blogPost.getBlogPostTitle());

            blogPostRepository.save(blogPost);

        } catch (Exception e) {
            log.info("", e);
            throw new RuntimeException("Could not save blogpost");
        }

    }


    //Create post
    public BlogPostResponse createPost(BlogPost blogPost) {
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        try {
            Optional<BlogPost> optionalBlogPost = blogPostRepository.findByBlogPostTitle(blogPost.getBlogPostTitle());
            if (optionalBlogPost.isPresent()) {
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_CREATE_FAIL_MSG"));
            } else {
                BlogPost newBlogPost = new BlogPost();
                newBlogPost.setBlogPostTitle(blogPost.getBlogPostTitle());
                newBlogPost.setBlogPostTag(blogPost.getBlogPostTag());
                newBlogPost.setBlogPostImageURL(blogPost.getBlogPostImageURL());
                newBlogPost.setBlogPostStatus(BlogPostStatus.Draft);
                newBlogPost.setBlogPostAuthor(blogPost.getBlogPostAuthor());
                newBlogPost.setBlogPostContent(blogPost.getBlogPostContent());
                newBlogPost.setBlogPostCreatedBy("No one");
                newBlogPost.setBlogPostModifiedBy("No one");
                newBlogPost.setBlogPostModifiedAt(new Date());
                newBlogPost.setBlogPostCreatedAt(new Date());

                //Call Save method
                savePost(newBlogPost);

                //API Response
                blogPostResponse.setBlogPost(newBlogPost);
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_SUCCESS_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_CREATE_SUCCESS"));
            }
        } catch (Exception e) {
            log.info("", e);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_CREATE_FAIL_MSG"));
        }
        return blogPostResponse;
    }


    public BlogPostResponse viewOneBlogPost(Long blogPostId) {
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        try {
            Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(blogPostId);
            if (optionalBlogPost.isPresent()) {
                BlogPost existingBlogPost = optionalBlogPost.get();
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_SUCCESS_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_RETRIEVE_SUCCESS"));
                blogPostResponse.setBlogPost(existingBlogPost);
            } else {
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_NON_EXISTENT"));

            }

        } catch (Exception e) {
            log.info("", e);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_RETRIEVE_FAIL_MSG"));

        }
        return  blogPostResponse;

    }

    public BlogPostResponse viewAllBlogPosts(){
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        try{
            List<BlogPost> blogPosts = blogPostRepository.findAll();
            blogPostResponse.setBlogPosts(blogPosts);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_SUCCESS_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_RETRIEVE_SUCCESS"));

        }catch (Exception e){
            log.info("", e);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_RETRIEVE_FAIL_MSG"));
        }
        return blogPostResponse;
    }


    public BlogPostResponse updateBlogPost(BlogPost blogPost) {
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        try {
            Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(blogPost.getBlogPostId());
            if (optionalBlogPost.isPresent()) {
                BlogPost existingBlogPost = optionalBlogPost.get();
                existingBlogPost.setBlogPostTitle(blogPost.getBlogPostTitle());
                existingBlogPost.setBlogPostTag(blogPost.getBlogPostTag());
                existingBlogPost.setBlogPostImageURL(blogPost.getBlogPostImageURL());
                existingBlogPost.setBlogPostStatus(blogPost.getBlogPostStatus());
                existingBlogPost.setBlogPostAuthor(blogPost.getBlogPostAuthor());
                existingBlogPost.setBlogPostContent(blogPost.getBlogPostContent());
                existingBlogPost.setBlogPostModifiedBy("No one");
                existingBlogPost.setBlogPostModifiedAt(new Date());

                blogPostRepository.save(existingBlogPost);
                blogPostResponse.setBlogPost(existingBlogPost);
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_SUCCESS_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_UPDATE_SUCCESS"));


            } else {
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_NON_EXISTENT"));
            }
        } catch (Exception e) {
            log.info("", e);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_UPDATE_FAIL"));
        }
        return blogPostResponse;
    }


    public BlogPostResponse deleteBlogPost(BlogPost blogPost){
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        try{
            Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(blogPost.getBlogPostId());
            if (optionalBlogPost.isPresent()) {
                BlogPost existingBlogPost = optionalBlogPost.get();
                existingBlogPost.setBlogPostDeletedBy("Someone");
                existingBlogPost.setBlogPostDeletedAt(new Date());

                blogPostRepository.save(existingBlogPost);
                blogPostResponse.setBlogPost(existingBlogPost);
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_SUCCESS_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_DELETE_SUCCESS"));


            } else {
                blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
                blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_NON_EXISTENT"));
            }
        }catch (Exception e){
            log.info("",e);
            blogPostResponse.setStatusCode(Settings.getInstance("").getProperty("BLOGPOST_FAIL_CODE"));
            blogPostResponse.setStatusMessage(Settings.getInstance("").getProperty("BLOGPOST_DELETE_FAIL"));

        }
        return blogPostResponse;
    }



}
