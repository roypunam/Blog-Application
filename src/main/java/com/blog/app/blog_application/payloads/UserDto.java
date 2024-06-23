package com.blog.app.blog_application.payloads;

import com.blog.app.blog_application.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {
	private Integer id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 character")
	private String name;
	@Email(message = "Email address is not valid!!")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars!!")
	private String password;
	@NotEmpty
	private String about;

	public static User getUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;

	}

}
