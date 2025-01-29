package com.haoranpu.blog.repository;

import com.haoranpu.blog.model.BlogPost;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    
    @Query("SELECT b FROM BlogPost b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<BlogPost> searchByTerm(@Param("term") String term);
}
