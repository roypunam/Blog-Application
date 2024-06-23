package com.blog.app.blog_application.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.app.blog_application.payloads.CommentDto;
import com.blog.app.blog_application.payloads.PostDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String postTitle;

	@Column(name = "content", nullable = false)
	private String postContent;

	@Column(name = "image_name")
	private String postImageName;

	@Column(name = "date")
	private Date addDate;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public static PostDto getPostDto(Post post) {
		PostDto dto = new PostDto();
		dto.setPostTitle(post.getPostTitle());
		dto.setContent(post.getPostContent());
		dto.setPostImageName(post.getPostImageName());
		return dto;
	}

	public static PostDto getPostDtoForView(Post post) {
		PostDto dto = new PostDto();
		dto.setPostTitle(post.getPostTitle());
		dto.setContent(post.getPostContent());
		dto.setPostImageName(post.getPostImageName());
		
		List<CommentDto> commentDtoList= new ArrayList<>();
		
		for(Comment comment: post.getComments()) {
			CommentDto commentDto = new CommentDto();
			commentDto.setCommentContent(comment.getCommentContent());
			commentDtoList.add(commentDto);
		}
		dto.setCommentList(commentDtoList);

		return dto;
	}

}
