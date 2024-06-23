package com.blog.app.blog_application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.Role;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.repository.RoleRepo;
import com.blog.app.blog_application.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public Role createRole(Role role) {
		Role newRole = roleRepo.save(role);
		return newRole;
	}

	@Override
	public void deleteRole(Integer roleId) {
		Role getRole = roleRepo.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role", "roleId", roleId));
		roleRepo.delete(getRole);
	}

}
