package com.haoranpu.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haoranpu.blog.Query;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;

@Service
public class GetBlogPostsService implements Query<String, List<BlogPost>> {
    
    private final BlogPostRepository blogPostRepository;

    public GetBlogPostsService(BlogPostRepository blogPostRepository){
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public ResponseEntity<List<BlogPost>> execute(String term) {
        List<BlogPost> blogPosts;

        if(term == null){
            blogPosts = blogPostRepository.findAll();
        } else {
            blogPosts = blogPostRepository.searchByTerm(term);
        }
        
        return ResponseEntity.ok(blogPosts);
    }
}
