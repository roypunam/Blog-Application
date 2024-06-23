package com.blog.app.blog_application.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blog.app.blog_application.entity.Role;
import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.repository.RoleRepo;



public class UserUserDetails implements UserDetails{

	@Autowired
	private RoleRepo roleRepo;
	
	private String email;
	private String password;
	private List<GrantedAuthority> authorities;
	User user=new User();
	public UserUserDetails(User user) {
		List<Role> roles=roleRepo.findAll();
		
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.authorities=roles.stream().map(role->role.getName()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//				Arrays.stream(roles).split(",").map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
