package com.haoranpu.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogPostNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBlogPostNotFoundException(BlogPostNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBlogPostNotValidConstraints(ConstraintViolationException exception){
        return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
    }
}
