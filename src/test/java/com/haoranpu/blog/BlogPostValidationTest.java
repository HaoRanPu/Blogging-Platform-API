package com.haoranpu.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.haoranpu.blog.model.BlogPost;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BlogPostValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void given_blog_post_when_null_fields_then_should_fail_validation() {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(null); // Should trigger @NotNull
        blogPost.setContent(null); // Should trigger @NotNull
        blogPost.setCategory(null); // Should trigger @NotNull
        blogPost.setTags(null); // Should trigger @NotNull

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size()); // Expecting 4 validation errors
    }

    @Test
    void given_blog_post_when_empty_tags_then_should_fail_validation() {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Valid Title");
        blogPost.setContent("Valid Content");
        blogPost.setCategory("Tech");
        blogPost.setTags(List.of()); // Empty list should trigger @Size(min = 1)

        Set<ConstraintViolation<BlogPost>> violations = validator.validate(blogPost);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }
}
