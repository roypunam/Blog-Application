package com.blog.app.blog_application.payloads;



import com.blog.app.blog_application.entity.Comment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	private String commentContent;

	public static Comment getComment(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setCommentContent(commentDto.getCommentContent());
		return comment;
	}

}
