package com.haoranpu.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.model.UpdateBlogPostCommand;
import com.haoranpu.blog.service.CreateBlogPostService;
import com.haoranpu.blog.service.DeleteBlogPostService;
import com.haoranpu.blog.service.GetBlogPostsService;
import com.haoranpu.blog.service.GetBlogPostService;
import com.haoranpu.blog.service.UpdateBlogPostService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/posts")
public class BlogPostController {

    CreateBlogPostService createBlogPostService;
    UpdateBlogPostService updateBlogPostService;
    DeleteBlogPostService deleteBlogPostService;
    GetBlogPostService getBlogPostService;
    GetBlogPostsService getBlogPostsService;
    
    public BlogPostController(CreateBlogPostService createBlogPostService, 
                              UpdateBlogPostService updateBlogPostService,
                              DeleteBlogPostService deleteBlogPostService, 
                              GetBlogPostService getBlogPostService,
                              GetBlogPostsService getAllBlogPostsService) {

        this.createBlogPostService = createBlogPostService;
        this.updateBlogPostService = updateBlogPostService;
        this.deleteBlogPostService = deleteBlogPostService;
        this.getBlogPostService = getBlogPostService;
        this.getBlogPostsService = getAllBlogPostsService;
    }

    @PostMapping
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        return createBlogPostService.execute(blogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        return updateBlogPostService.execute(new UpdateBlogPostCommand(id, blogPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return deleteBlogPostService.execute(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPost(@PathVariable Long id) {
        return getBlogPostService.execute(id);
    }
    
    @GetMapping
    public ResponseEntity<List<BlogPost>> getPosts(@RequestParam(required = false) String term) {
        return getBlogPostsService.execute(term);
    }
    
}
