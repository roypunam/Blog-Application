package com.blog.app.blog_application.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.blog_application.payloads.PostDto;
import com.blog.app.blog_application.service.FileService;
import com.blog.app.blog_application.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/image")
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private PostService postService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/upload/post/{postId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PostDto> imageUpload(@RequestParam("image") MultipartFile image, @PathVariable Integer postId)
			throws IOException {

		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.uploadImage(path, image);
		postDto.setPostImageName(fileName);
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	@GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	@PreAuthorize("hasAuthority('USER')")
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
