package com.blog.app.blog_application.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.Category;
import com.blog.app.blog_application.entity.Post;
import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.payloads.PostDto;
import com.blog.app.blog_application.payloads.PostResponse;
import com.blog.app.blog_application.repository.CategoryRepo;
import com.blog.app.blog_application.repository.PostRepo;
import com.blog.app.blog_application.repository.UserRepo;
import com.blog.app.blog_application.service.PostService;



@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Post post=PostDto.getPost(postDto);
		post.setUser(user);
		post.setCategory(category);
		post.setAddDate(new Date());
		Post newPost=postRepo.save(post);
		return Post.getPostDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getContent());
		post.setPostImageName(postDto.getPostImageName());
		post.setAddDate(new Date());
		Post updatedPost=postRepo.save(post);
		return Post.getPostDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.deleteById(postId);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		Sort sort=null;
		if(sortDirection.equalsIgnoreCase("ASC")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> postList=pagePost.getContent();
		List<PostDto> allPost = postList.stream().map(post -> Post.getPostDtoForView(post)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(allPost);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		return Post.getPostDtoForView(post);
	}

	@Override
	public PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
		
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost=postRepo.findAllByCategory(cat,p);
		List<Post> posts=pagePost.getContent();
		List<PostDto> postDtos = posts.stream().map(post-> Post.getPostDtoForView(post)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getAllPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts=postRepo.findAllByUser(user,p);
		List<Post> posts=pagePosts.getContent();
		List<PostDto> postDtos = posts.stream().map(post-> Post.getPostDtoForView(post)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts= postRepo.findByPostTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map(post->Post.getPostDtoForView(post)).collect(Collectors.toList())	;
		return postDtos;
	}

}
