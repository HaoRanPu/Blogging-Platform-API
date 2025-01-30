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
import org.springframework.http.ResponseEntity;

import com.haoranpu.blog.model.BlogPost;
import com.haoranpu.blog.repository.BlogPostRepository;
import com.haoranpu.blog.service.GetBlogPostsService;

public class GetBlogPostsServiceTest {
     @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private GetBlogPostsService getBlogPostsService;

    private BlogPost blogPost;
    private BlogPost blogPost2;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setTitle("My First Blog Post");
        blogPost.setContent("This is the content of my first blog post.");
        blogPost.setCategory("Technology");
        blogPost.setTags(List.of("Tech", "Programming"));

        blogPost2 = new BlogPost();
        blogPost2.setId(2L);
        blogPost2.setTitle("My Second Blog Post");
        blogPost2.setContent("This is the content of my Second blog post.");
        blogPost2.setCategory("Technology");
        blogPost2.setTags(List.of("Tech", "Programming"));
    }

    @Test
    public void given_blog_posts_exists_when_get_blog_posts_service_with_null_term_return_all_blog_posts(){
        when(blogPostRepository.findAll()).thenReturn(List.of(blogPost, blogPost2));

        ResponseEntity<List<BlogPost>> response = getBlogPostsService.execute(null);

        assertEquals(ResponseEntity.ok(List.of(blogPost, blogPost2)), response);
        verify(blogPostRepository, times(1)).findAll();
    }

    @Test
    public void given_blog_posts_exists_when_get_blog_posts_service_with_term_return_filtered_blog_post(){
        String searchTerm = "First";

        when(blogPostRepository.searchByTerm(searchTerm)).thenReturn(List.of(blogPost));

        ResponseEntity<List<BlogPost>> response = getBlogPostsService.execute(searchTerm);

        assertEquals(ResponseEntity.ok(List.of(blogPost)), response);
        verify(blogPostRepository, times(1)).searchByTerm(searchTerm);
    }
}
