package com.blog.app.blog_application.service;

import java.util.List;

import com.blog.app.blog_application.payloads.PostDto;
import com.blog.app.blog_application.payloads.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto, Integer postId, Integer categoryId);

	public PostDto updatePost(PostDto postDto, Integer postId);

	public void deletePost(Integer postId);

	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	public PostDto getPostById(Integer postId);

	public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

	public PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

	public List<PostDto> searchPosts(String keyword);

}
