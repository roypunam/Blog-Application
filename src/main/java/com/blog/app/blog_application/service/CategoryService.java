package com.blog.app.blog_application.service;

import java.util.List;

import com.blog.app.blog_application.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteCategory(Integer categoryId);

	public CategoryDto getCategory(Integer categoryId);

	public List<CategoryDto> getCategories();

}
