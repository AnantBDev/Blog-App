package com.anantbhardwaj.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.anantbhardwaj.blog.entities.Category;
import com.anantbhardwaj.blog.entities.Comment;
import com.anantbhardwaj.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	//private String imageName="default.png";
	
	private String imageName;
	
	private Date addedDate;
	
	//To prevent Recursion as by keeping it as User, there was posts list in user which was again calling user
	private UserDto user;
	
	private CategoryDto category;
	
	//avoids Get Mapping of Comment
	private Set<CommentDto> comments=new HashSet<>();
}
