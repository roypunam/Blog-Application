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
import com.blog.app.blog_application.payloads.CategoryDto;
import com.blog.app.blog_application.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategoryDto = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
	}

	@PutMapping("/edit/{categoryId}")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<CategoryDto> EditCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer categoryId) {
		CategoryDto updateCategoryDto = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategoryDto, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}/delete")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/search/{categoryId}")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<CategoryDto> searchCategory(@PathVariable Integer categoryId) {
		CategoryDto getCategoryDto = categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(getCategoryDto, HttpStatus.OK);
	}

	@GetMapping("/categories")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<List<CategoryDto>> categoryList() {
		List<CategoryDto> categoriesDto = categoryService.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categoriesDto, HttpStatus.OK);
	}

}
