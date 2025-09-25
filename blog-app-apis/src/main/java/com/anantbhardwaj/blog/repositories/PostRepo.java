package com.anantbhardwaj.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anantbhardwaj.blog.entities.Category;
import com.anantbhardwaj.blog.entities.Post;
import com.anantbhardwaj.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	//Custom finder method - find all users by user
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//J-Query - Implementing searching for a keyword specifically in title of a post
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	
}
