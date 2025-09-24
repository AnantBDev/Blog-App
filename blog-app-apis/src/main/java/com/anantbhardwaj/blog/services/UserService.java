package com.anantbhardwaj.blog.services;

import java.util.List;

import com.anantbhardwaj.blog.payloads.UserDto;
//Not to be autowired as it is an interface
public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
