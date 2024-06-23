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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.blog_application.config.AppConstant;
import com.blog.app.blog_application.payloads.ApiResponse;
import com.blog.app.blog_application.payloads.PostDto;
import com.blog.app.blog_application.payloads.PostResponse;
import com.blog.app.blog_application.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/add")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto newPostDto=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPostDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/edit/{postId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostDto> editPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatedPostDto=postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}/delete")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<PostResponse> viewPosts(@RequestParam(value="pageNumber",defaultValue = AppConstant.PAGE_NUMBER , required=false)
													Integer pageNumber,
													@RequestParam(value="pageSize",defaultValue=AppConstant.PAGE_SIZE,required = false)
													Integer pageSize,
													@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)
													String sortBy,
													@RequestParam(value = "sortDirection",defaultValue = AppConstant.SORT_DIRECTION,required = false)
													String sortDirection){
		PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		
	}
	
	@GetMapping("/search/{postId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostDto> searchPostById(@PathVariable Integer postId){
		PostDto postDto=postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/search/category/{categoryId}")
	@PreAuthorize("hasAuthority('USER','ADMIN')")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
														@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMBER,required=false)
														Integer pageNumber,
														@RequestParam(value="pageSize",defaultValue=AppConstant.PAGE_SIZE,required=false)
														Integer pageSize){
		PostResponse postResponse=postService.getAllPostByCategory(categoryId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/search/user/{userId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
														@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMBER,required=false)
														Integer pageNumber,
														@RequestParam(value="pageSize",defaultValue=AppConstant.PAGE_SIZE,required=false)
														Integer pageSize){
		PostResponse postResponse=postService.getAllPostByUser(userId,pageNumber,pageSize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keyword){
		List<PostDto> searchedPost=postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchedPost,HttpStatus.OK);
	}
	

	

}
