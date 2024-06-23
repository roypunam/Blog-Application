package com.blog.app.blog_application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.Category;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.payloads.CategoryDto;
import com.blog.app.blog_application.repository.CategoryRepo;
import com.blog.app.blog_application.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = CategoryDto.getCategory(categoryDto);
		Category savedCatagory = categoryRepo.save(category);
		return Category.getCategoryDto(savedCatagory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category savedCatagory = categoryRepo.save(category);
		return Category.getCategoryDto(savedCatagory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		categoryRepo.deleteById(categoryId);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return Category.getCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category category : categories) {
			CategoryDto categoryDto = Category.getCategoryDto(category);
			categoryDtos.add(categoryDto);
		}
		return categoryDtos;
	}

}
