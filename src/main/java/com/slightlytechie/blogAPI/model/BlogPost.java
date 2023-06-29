package com.slightlytechie.blogAPI.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "blog_seq")
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date blogPostCreatedAt;

    @Column(name = "blog_post_created_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String blogPostCreatedBy;

    @Column(name = "blog_post_modified_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String blogPostModifiedBy;

    @Column(name = "blog_post_modified_at")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date blogPostModifiedAt;

    @Column(name = "blog_post_deleted_by")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String blogPostDeletedBy;

    @Column(name = "blog_post_deleted_at")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date blogPostDeletedAt;






}
