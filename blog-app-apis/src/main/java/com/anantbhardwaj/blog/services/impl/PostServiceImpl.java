package com.anantbhardwaj.blog.services.impl;

import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anantbhardwaj.blog.entities.Category;
import com.anantbhardwaj.blog.entities.Post;
import com.anantbhardwaj.blog.entities.User;
import com.anantbhardwaj.blog.exceptions.ResourceNotFoundException;
import com.anantbhardwaj.blog.payloads.PostDto;
import com.anantbhardwaj.blog.payloads.PostResponse;
import com.anantbhardwaj.blog.repositories.CategoryRepo;
import com.anantbhardwaj.blog.repositories.PostRepo;
import com.anantbhardwaj.blog.repositories.UserRepository;
import com.anantbhardwaj.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	//Create Posts
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		Category category=this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category" , "CategoryId", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	//Update Post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).
				orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId)); 
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	//GetPostById
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	//getAllPosts
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		//PageSize=No of posts on a particular page Number
		//made sorting custom based
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> posts=pagePost.getContent();
		
		List<PostDto> postDtos = posts.stream().map(post-> 
			this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	//getPostBycategory
	@Override
	public List<PostDto> getPostbyCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id", categoryId));
		
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).
				collect(Collectors.toList()); 
		return postDtos;
	}

	@Override
	public List<PostDto> getPostbyUser(Integer userId) {
		User user=this.userRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)-> 
				this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).
			orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

}
