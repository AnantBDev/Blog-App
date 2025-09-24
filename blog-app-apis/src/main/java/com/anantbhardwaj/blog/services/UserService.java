package com.anantbhardwaj.blog.services;

import java.util.List;

import com.anantbhardwaj.blog.payloads.UserDto;
import com.anantbhardwaj.blog.payloads.UserResponse;
//Not to be autowired as it is an interface
public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	UserResponse getAllUsers(Integer PageNumber, Integer PageSize);
	
	void deleteUser(Integer userId);
	
}
