package com.anantbhardwaj.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anantbhardwaj.blog.payloads.ApiResponse;
import com.anantbhardwaj.blog.payloads.CommentDto;
import com.anantbhardwaj.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("post/{userId}/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentdto,
			@PathVariable Integer postId,@PathVariable Integer userId){
		CommentDto commentDto = this.commentService.createComment(commentdto, postId, userId);
				return new ResponseEntity<CommentDto>(commentDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!", true),HttpStatus.OK);
	}

}
