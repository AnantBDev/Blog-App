package com.anantbhardwaj.blog.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anantbhardwaj.blog.payloads.ApiResponse;
import com.anantbhardwaj.blog.payloads.PostDto;
import com.anantbhardwaj.blog.payloads.PostResponse;
import com.anantbhardwaj.blog.services.PostService;

//Pagination= Page Size+Page No-nearly implmented by Springboot
//JpaReposiroty already extends PagingAndSorting Repository
//Sorting by One field
//http://localhost:8080/posts?pageSize=5&PageNo=2&SortBy=title

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	//Create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto newpost=this.postService.createPost(postDto, userId, categoryId);		
		return new ResponseEntity<PostDto>(newpost,HttpStatus.CREATED);
	}
	
	//UpdatePosts
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, 
			@PathVariable Integer postId){
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	//GetPostsById
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	//GetAllPosts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required=false)Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = "postId",required = false)String sortBy,
			@RequestParam(value="sortDir",defaultValue = "ASC",required = false) String sortDir)
	{
		 PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	//GetPostsByUser
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostbyUser(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	
	//GetPostsByCategory
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostbyCategory(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	//DeletePosts
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully",true),HttpStatus.OK);
	}
}
