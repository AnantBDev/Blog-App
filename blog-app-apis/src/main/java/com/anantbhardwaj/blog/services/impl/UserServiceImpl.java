package com.anantbhardwaj.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anantbhardwaj.blog.entities.User;
import com.anantbhardwaj.blog.exceptions.*;
import com.anantbhardwaj.blog.payloads.UserDto;
import com.anantbhardwaj.blog.repositories.UserRepository;
import com.anantbhardwaj.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	//If any Apis is to be made- add a method in service and implement in service impl class
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=DtoToUser(userDto);
		
		User savedUser=this.userRepo.save(user);
		
		return UserToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User", "Id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1= this.UserToDto(updatedUser);
		return userDto1; 
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "Id", userId));
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		//used lambda stream APi to convert list of users to list of userDtos
		List<UserDto> userDtos=users.stream().map(user->this.UserToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","Id", userId));
		this.userRepo.delete(user);
	}
	
	
	//Can be done using ModelMappers Library
	private User DtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	//To know about internal working of Model mapper- official website= How Model mapper works?
	//Other methods also to map model but still user and userDto should have same parameters
	private UserDto UserToDto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}
}
