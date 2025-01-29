package com.haoranpu.blog.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haoranpu.blog.Command;
import com.haoranpu.blog.exceptions.BlogPostNotFoundException;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.model.UpdateBlogPostCommand;
import com.haoranpu.blog.repository.BlogPostRepository;

@Service
public class UpdateBlogPostService implements Command<UpdateBlogPostCommand, BlogPost> {

    private final BlogPostRepository blogPostRepository;

    public UpdateBlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public ResponseEntity<BlogPost> execute(UpdateBlogPostCommand input) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(input.getId());

        if(blogPostOptional.isPresent()){
            BlogPost oldBlogPost = blogPostOptional.get();
            BlogPost newBlogPost = input.getBlogPost();
            newBlogPost.setId(input.getId());
            newBlogPost.setCreatedAt(oldBlogPost.getCreatedAt());
            newBlogPost.setUpdatedAt(LocalDateTime.now());
            blogPostRepository.save(newBlogPost);

            return ResponseEntity.ok(newBlogPost);
        }

        throw new BlogPostNotFoundException();
    }

}
