package com.anantbhardwaj.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anantbhardwaj.blog.entities.Category;
import com.anantbhardwaj.blog.exceptions.ResourceNotFoundException;
import com.anantbhardwaj.blog.payloads.CategoryDto;
import com.anantbhardwaj.blog.repositories.CategoryRepo;
import com.anantbhardwaj.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	//Used to convert one object type to other
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(() ->new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class); 
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(() ->new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category" , "Category Id", categoryId));
		CategoryDto catDto=this.modelMapper.map(cat, CategoryDto.class);
		return catDto;
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> CatDtos = categories.stream().map((cat)-> 
					this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return CatDtos;
	}

}
