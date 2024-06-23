package com.blog.app.blog_application.payloads;

import com.blog.app.blog_application.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	@NotBlank
	@Size(min = 4, message = "Min size of category description is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10, message = "Min size of category description is 10")
	private String categoryDescription;

	public static Category getCategory(CategoryDto categoryDto) {
		com.blog.app.blog_application.entity.Category category = new Category();
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return category;
	}

}
