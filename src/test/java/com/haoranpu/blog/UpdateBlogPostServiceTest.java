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
import com.haoranpu.blog.model.UpdateBlogPostCommand;
import com.haoranpu.blog.repository.BlogPostRepository;
import com.haoranpu.blog.service.UpdateBlogPostService;

public class UpdateBlogPostServiceTest {
    
    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private UpdateBlogPostService updateBlogPostService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_blog_post_exists_when_Update_blog_post_service_return_Update_blog_post(){
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("My First Blog Post");
        blogPost.setContent("This is the content of my first blog post.");
        blogPost.setCategory("Technology");
        blogPost.setTags(List.of("Tech", "Programming"));
        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));

        BlogPost updateBlogPost = new BlogPost();
        updateBlogPost.setId(1L);
        updateBlogPost.setTitle("My First Blog Post Update");
        updateBlogPost.setContent("This is the content of my first blog post. Update");
        updateBlogPost.setCategory("Technology");
        updateBlogPost.setTags(List.of("Tech", "Programming", "Update"));
        when(blogPostRepository.save(updateBlogPost)).thenReturn(updateBlogPost);

        ResponseEntity<BlogPost> response = updateBlogPostService.execute(new UpdateBlogPostCommand((long) 1, updateBlogPost));

        assertEquals(ResponseEntity.ok(updateBlogPost), response);
        verify(blogPostRepository, times(1)).findById(1L);
        verify(blogPostRepository, times(1)).save(updateBlogPost);
    }

    @Test
    public void given_blog_post_not_exists_when_update_blog_post_service_throw_blog_post_not_found_exception(){
        when(blogPostRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BlogPostNotFoundException.class, () -> updateBlogPostService.execute(new UpdateBlogPostCommand((long) 1, null)));
        verify(blogPostRepository, times(1)).findById(1L);
    }
}
