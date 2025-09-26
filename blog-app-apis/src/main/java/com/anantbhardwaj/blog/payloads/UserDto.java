package com.anantbhardwaj.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.anantbhardwaj.blog.entities.Comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//3steps to have validation in Project - 1.Dependency, 2. Bean Validation annotation, 3. Valid annotation before Request Body
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
//SImilar to User but not same; User can have other fields as well- dynamically controlled
	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username should be minimum of 4 characters!")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message ="Password must be minimum of 3 chars and max of 10 chars")
	//can use pattern annotation for particular pattern
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<CommentDto> comments=new HashSet<>();
}
