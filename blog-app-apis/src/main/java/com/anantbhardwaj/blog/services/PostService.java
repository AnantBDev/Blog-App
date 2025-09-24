package com.anantbhardwaj.blog.services;

import java.util.List;

import com.anantbhardwaj.blog.payloads.PostDto;
import com.anantbhardwaj.blog.payloads.PostResponse;

public interface PostService {

	//create 
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//Update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//Get
	PostDto getPostById(Integer postId);
	
	//Getall
	PostResponse getAllPosts(Integer PageNumber, Integer PageSize, String sortBy,String sortDir);
	
	//GetPost by Catgeory
	List<PostDto> getPostbyCategory(Integer categoryId);
	
	//Get Post by User
	List<PostDto> getPostbyUser(Integer userId);
	
	//Get posts by keyword
	List<PostDto> searchPosts(String keyword);
	
	//Delete
	void deletePost(Integer postId);
}
