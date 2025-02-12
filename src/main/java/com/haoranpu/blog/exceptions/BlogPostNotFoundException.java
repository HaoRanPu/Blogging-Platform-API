package com.haoranpu.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogPostNotFoundException extends RuntimeException {

    public BlogPostNotFoundException(){
        super("Blog Post Not Found");
    }
}
