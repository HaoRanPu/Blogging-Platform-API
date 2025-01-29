package com.haoranpu.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
public class BlogPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "content is required")
    private String content;

    @NotNull(message = "category is required")
    private String category;

    @ElementCollection
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @NotNull(message = "tags is required")
    @Size(min = 1, message = "At least one tag is required")
    private List<String> tags;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
