package com.anantbhardwaj.blog;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//Task -24/9- Implement Pagination on Users - Done and categories
//Anant-Already a configuration class as annotated with SpringBootApplication.Hence bean can be declared here 
//or in config pkg. 
@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	//highly used to convert one object type to other - here, UserDto to User and vice versa
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
