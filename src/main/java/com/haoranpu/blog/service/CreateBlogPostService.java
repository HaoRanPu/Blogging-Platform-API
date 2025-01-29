package com.haoranpu.blog.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haoranpu.blog.Command;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;

@Service
public class CreateBlogPostService implements Command<BlogPost, BlogPost> {

    private final BlogPostRepository blogPostRepository;

    public CreateBlogPostService(BlogPostRepository blogPostRepository){
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public ResponseEntity<BlogPost> execute(BlogPost blogPost) {
        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setUpdatedAt(LocalDateTime.now());
        BlogPost saveBlogPost = blogPostRepository.save(blogPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveBlogPost);
    }
    
}
