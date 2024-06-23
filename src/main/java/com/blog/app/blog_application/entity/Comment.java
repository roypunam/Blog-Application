package com.blog.app.blog_application.entity;

import com.blog.app.blog_application.payloads.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer commentId;

	@Column(name = "comment_content")
	private String commentContent;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne
	private User user;

	public static CommentDto getCommentDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCommentContent(comment.getCommentContent());
		return commentDto;
	}

}
