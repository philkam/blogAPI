package com.slightlytechie.blogAPI.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "blog_post")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
    @Column(name = "blog_post_id")
    private Long blogPostId;

    @Column(name = "blog_post_title")
    private String blogPostTitle;

    @Column(name = "blog_post_content")
    private String blogPostContent;

    @Column(name = "blog_post_author")
    private String blogPostAuthor;

    @Column(name = "blog_post_tag")
    private String blogPostTag;

    @Column(name = "blog_post_image_url")
    private String blogPostImageURL;

    @Column(name = "blog_post_status")
    private BlogPostStatus blogPostStatus;

    @Column(name = "blog_post_created_at")
    private Date blogPostCreatedAt;

    @Column(name = "blog_post_created_by")
    private String blogPostCreatedBy;

    @Column(name = "blog_post_modified_by")
    private String blogPostModifiedBy;

    @Column(name = "blog_post_modified_at")
    private Date blogPostModifiedAt;

    @Column(name = "blog_post_deleted_by")
    private String blogPostDeletedBy;

    @Column(name = "blog_post_deleted_at")
    private String blogPostDeletedAt;






}
