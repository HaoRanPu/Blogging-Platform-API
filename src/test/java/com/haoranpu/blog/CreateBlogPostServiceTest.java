package com.haoranpu.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;
import com.haoranpu.blog.service.CreateBlogPostService;

public class CreateBlogPostServiceTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private CreateBlogPostService createBlogPostService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void given_blog_post_when_create_blog_post_service_return_created_blog_post() {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("My First Blog Post");
        blogPost.setContent("This is the content of my first blog post.");
        blogPost.setCategory("Technology");
        blogPost.setTags(List.of("Tech", "Programming"));

        BlogPost savedBlogPost = new BlogPost();
        savedBlogPost.setId(1L);
        savedBlogPost.setTitle("My First Blog Post");
        savedBlogPost.setContent("This is the content of my first blog post.");
        savedBlogPost.setCategory("Technology");
        savedBlogPost.setTags(List.of("Tech", "Programming"));
        when(blogPostRepository.save(blogPost)).thenReturn(savedBlogPost);

        ResponseEntity<BlogPost> response = createBlogPostService.execute(blogPost);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(savedBlogPost), response);
        verify(blogPostRepository, times(1)).save(blogPost);
    }
}
