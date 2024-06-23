package com.blog.app.blog_application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.blog_application.payloads.ApiResponse;
import com.blog.app.blog_application.payloads.UserDto;
import com.blog.app.blog_application.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/edit/{userId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userId) {
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}/delete")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);

	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> userList = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(userList, HttpStatus.OK);

	}

	@GetMapping("/search/{userId}")
	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);

	}
}
