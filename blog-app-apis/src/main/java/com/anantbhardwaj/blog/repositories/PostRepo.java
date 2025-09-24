package com.anantbhardwaj.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anantbhardwaj.blog.entities.Category;
import com.anantbhardwaj.blog.entities.Post;
import com.anantbhardwaj.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	//Custom finder method - find all users by user
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
