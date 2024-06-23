package com.blog.app.blog_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
	private Integer roleId;

	@Id
	@JoinColumn(name = "id", nullable = false)
	private String name;
}
