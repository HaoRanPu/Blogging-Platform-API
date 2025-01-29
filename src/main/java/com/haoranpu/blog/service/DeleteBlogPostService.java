package com.haoranpu.blog.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haoranpu.blog.Command;
import com.haoranpu.blog.exceptions.BlogPostNotFoundException;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;

@Service
public class DeleteBlogPostService implements Command<Long, Void> {

    private final BlogPostRepository blogPostRepository;

    public DeleteBlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Long id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if (blogPostOptional.isPresent()){
            blogPostRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new BlogPostNotFoundException();
    }
}
