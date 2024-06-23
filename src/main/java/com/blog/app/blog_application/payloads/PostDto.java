package com.blog.app.blog_application.payloads;

import java.util.List;

import com.blog.app.blog_application.entity.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
	
	@NotBlank
	@Size(min = 5, message = "Post Title must be min of 5 characters")
	private String postTitle;
	@NotBlank
	@Size(min = 10, message = "content must be min of 10 characters")
	private String content;

	private String postImageName;

	private List<CommentDto> commentList;

	public static Post getPost(PostDto postDto) {
		Post post = new Post();
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getContent());
		post.setPostImageName(postDto.getPostImageName());
		
		return post;
	}

}
