package com.blog.app.blog_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.blog_application.entity.Role;
import com.blog.app.blog_application.payloads.ApiResponse;
import com.blog.app.blog_application.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Role> addNewRole(@RequestBody Role role) {
		Role createdRole = roleService.createRole(role);
		return new ResponseEntity<Role>(createdRole, HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/{roleId}")
	public ResponseEntity<ApiResponse> deleteExistingRole(@PathVariable Integer roleId) {
		roleService.deleteRole(roleId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Role deleted successfully", true), HttpStatus.OK);
	}
}
