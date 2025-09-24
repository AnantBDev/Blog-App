package com.anantbhardwaj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anantbhardwaj.blog.payloads.ApiResponse;
import com.anantbhardwaj.blog.payloads.CategoryDto;
import com.anantbhardwaj.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	//Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto){
	CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto,@PathVariable Integer catId){
	CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,catId);
	return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}
	
	
	//Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
	this.categoryService.deleteCategory(catId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully", true), HttpStatus.OK);
	}
	
	//Get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
	CategoryDto categoryDto=this.categoryService.getCategory(catId);
	return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	
	//GetAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		
	List<CategoryDto> categories=this.categoryService.getCategories();
	return ResponseEntity.ok(categories);
	}
}
