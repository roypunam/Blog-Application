package com.blog.app.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.blog_application.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
