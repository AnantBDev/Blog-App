package com.anantbhardwaj.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anantbhardwaj.blog.entities.Comment;
import com.anantbhardwaj.blog.entities.Post;
import com.anantbhardwaj.blog.entities.User;
import com.anantbhardwaj.blog.exceptions.ResourceNotFoundException;
import com.anantbhardwaj.blog.payloads.CommentDto;
import com.anantbhardwaj.blog.repositories.CommentRepo;
import com.anantbhardwaj.blog.repositories.PostRepo;
import com.anantbhardwaj.blog.repositories.UserRepository;
import com.anantbhardwaj.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId).
				orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).
				orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentRepo.delete(comment);
		
	}

}
