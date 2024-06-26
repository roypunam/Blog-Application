package com.blog.app.blog_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.blog_application.payloads.ApiResponse;
import com.blog.app.blog_application.payloads.CommentDto;
import com.blog.app.blog_application.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/new/user/{userId}/post/{postId}/add")
	@Secured({"ADMIN","USER"})
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,
			@PathVariable Integer userId) {
		CommentDto createCommentDto = commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createCommentDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}/delete")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
	}

}
