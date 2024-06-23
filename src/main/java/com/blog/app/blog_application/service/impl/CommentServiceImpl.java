package com.blog.app.blog_application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.blog_application.entity.Comment;
import com.blog.app.blog_application.entity.Post;
import com.blog.app.blog_application.entity.User;
import com.blog.app.blog_application.exception.ResourceNotFoundException;
import com.blog.app.blog_application.payloads.CommentDto;
import com.blog.app.blog_application.repository.CommentRepo;
import com.blog.app.blog_application.repository.PostRepo;
import com.blog.app.blog_application.repository.UserRepo;
import com.blog.app.blog_application.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		User user=userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		Comment comment=CommentDto.getComment(commentDto);
		comment.setPost(post);;
		comment.setUser(user);
		Comment savedComment=commentRepo.save(comment);
		return Comment.getCommentDto(savedComment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment getComment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId",commentId));
		commentRepo.delete(getComment);
	}

}
