package com.blog.app.blog_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.repository.UserRepo;

@Service
public class CustomUserDetaillService implements UserDetailsService{

	@Autowired
	 private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "email", username));
		return user;
	}

}
