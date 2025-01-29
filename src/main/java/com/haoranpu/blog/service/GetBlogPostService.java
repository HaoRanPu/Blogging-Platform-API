package com.haoranpu.blog.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haoranpu.blog.Query;
import com.haoranpu.blog.exceptions.BlogPostNotFoundException;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;

@Service
public class GetBlogPostService implements Query<Long, BlogPost> {
    
    private final BlogPostRepository blogPostRepository;

    public GetBlogPostService(BlogPostRepository blogPostRepository){
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public ResponseEntity<BlogPost> execute(Long input) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(input);

        if(blogPostOptional.isPresent()){
            return ResponseEntity.ok(blogPostOptional.get());
        }

        throw new BlogPostNotFoundException();
    }
}
