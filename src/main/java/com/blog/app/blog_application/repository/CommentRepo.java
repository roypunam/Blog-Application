package com.blog.app.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.blog_application.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
