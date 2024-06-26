package com.blog.app.blog_application.entity;

import java.util.ArrayList;
import java.util.List;

import com.blog.app.blog_application.payloads.CategoryDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	
	@Column(name="title",length=100,nullable=false)
	private String categoryTitle;
	@Column(name="description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	List<Post> posts=new ArrayList<>();
	
	
	public static CategoryDto getCategoryDto(Category category) {
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setCategoryId(category.getCategoryId());
		categoryDto.setCategoryTitle(category.getCategoryTitle());
		categoryDto.setCategoryDescription(category.categoryDescription);
		return categoryDto;
	}

}
