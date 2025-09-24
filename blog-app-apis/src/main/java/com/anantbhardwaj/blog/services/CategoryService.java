package com.anantbhardwaj.blog.services;

import java.util.List;

import com.anantbhardwaj.blog.payloads.CategoryDto;

public interface CategoryService {

	//Create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//Delete
	void deleteCategory(Integer categoryId);
	
	//GetOne
	//Interface mathods by default public and Abstract
	//Interface=Loose Coupling
	CategoryDto getCategory(Integer categoryId);
	
	//GetAll
	List<CategoryDto> getCategories();
}
