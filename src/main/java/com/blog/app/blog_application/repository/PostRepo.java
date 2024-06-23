package com.blog.app.blog_application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.blog_application.entity.Category;
import com.blog.app.blog_application.entity.Post;
import com.blog.app.blog_application.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	Page<Post> findAllByCategory(Category cat, Pageable pageable);

	Page<Post> findAllByUser(User user, Pageable pageable);

	List<Post> findByPostTitleContaining(String keyword);

}
