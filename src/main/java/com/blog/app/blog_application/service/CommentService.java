package com.blog.app.blog_application.service;

import com.blog.app.blog_application.payloads.CommentDto;

public interface CommentService {
	
public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId) ;
	
	public void deleteComment(Integer commentId);

}
