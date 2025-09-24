package com.anantbhardwaj.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anantbhardwaj.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
