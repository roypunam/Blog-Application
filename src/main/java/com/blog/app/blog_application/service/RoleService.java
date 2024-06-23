package com.blog.app.blog_application.service;

import com.blog.app.blog_application.entity.Role;

public interface RoleService {

	Role createRole(Role role);

	void deleteRole(Integer roleId);

}
