package com.blog.app.blog_application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.payloads.UserDto;
import com.blog.app.blog_application.repository.UserRepo;
import com.blog.app.blog_application.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = UserDto.getUser(userDto);
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User savedUser = userRepo.save(user);
		return User.getUserDto(savedUser);

	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User updateUser = userRepo.save(user);
		UserDto userDto2 = User.getUserDto(updateUser);
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return User.getUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> getUser = userRepo.findAll();

		// ****Using lambda expression****

		List<UserDto> allUser = getUser.stream().map(user -> User.getUserDto(user)).collect(Collectors.toList());

		// ****Using foreach loop****

//		List<UserDto> allUser=new ArrayList<UserDto>();
//		for (User user : getUser) {
//			UserDto user1=User.getUserDto(user);
//			allUser.add(user1);
//		}
		return allUser;

	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepo.delete(user);
	}

}
