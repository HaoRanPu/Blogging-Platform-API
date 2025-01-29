package com.haoranpu.blog.model;

import lombok.Getter;

@Getter
public class UpdateBlogPostCommand {
    private Long id;
    private BlogPost blogPost;
    public UpdateBlogPostCommand(Long id, BlogPost blogPost) {
        this.id = id;
        this.blogPost = blogPost;
    }
}
