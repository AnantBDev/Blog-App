package com.anantbhardwaj.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anantbhardwaj.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
