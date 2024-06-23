package com.blog.app.blog_application.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.repository.UserRepo;


@Component
public class UserUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user= userRepo.findByEmail(username);
		return user.map(UserUserDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("User not found"+username));
		
	}

}
