package com.haoranpu.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.haoranpu.blog.exceptions.BlogPostNotFoundException;
import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;
import com.haoranpu.blog.service.GetBlogPostService;

public class GetBlogPostServiceTest {
    
    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private GetBlogPostService getBlogPostService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_blog_post_exists_when_get_blog_post_service_return_blog_post(){
        BlogPost blogPost = new BlogPost();
        blogPost.setId((long) 1);
        blogPost.setTitle("My First Blog Post");
        blogPost.setContent("This is the content of my first blog post.");
        blogPost.setCategory("Technology");
        blogPost.setTags(List.of("Tech", "Programming"));
        when(blogPostRepository.findById((long) 1)).thenReturn(Optional.of(blogPost));

        ResponseEntity<BlogPost> response = getBlogPostService.execute((long) 1);

        assertEquals(ResponseEntity.ok(blogPost), response);
        verify(blogPostRepository, times(1)).findById((long) 1);
    }

    @Test
    public void given_blog_post_not_exists_when_get_blog_post_service_throw_blog_post_not_found_exception(){
        when(blogPostRepository.findById((long) 1)).thenReturn(Optional.empty());

        assertThrows(BlogPostNotFoundException.class, () -> getBlogPostService.execute((long) 1));
        verify(blogPostRepository, times(1)).findById((long) 1);
    }
}
