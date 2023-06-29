package com.slightlytechie.blogAPI.repository;

import com.slightlytechie.blogAPI.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {

    Optional<BlogPost> findByBlogPostTitle(String blogPostTitle);

    @Query("Select bp from BlogPost bp where bp.blogPostId = ?1 and bp.blogPostDeletedAt is null")
    Optional<BlogPost> findById(Long blogPostId);

    @Query("Select bp from BlogPost bp where  bp.blogPostDeletedAt is null")
    List<BlogPost> findAll();



}
