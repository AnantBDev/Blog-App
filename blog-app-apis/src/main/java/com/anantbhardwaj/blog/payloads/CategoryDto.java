package com.anantbhardwaj.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	//Id is also part of Dto
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="Size must be minimum of 4 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10)
	private String categoryDescription;
}
