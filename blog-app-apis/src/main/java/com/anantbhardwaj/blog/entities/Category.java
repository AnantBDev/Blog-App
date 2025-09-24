package com.anantbhardwaj.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="Title",length=100, nullable=false)
	private String categoryTitle;
	
	@Column(name="Description")
	private String categoryDescription;

	//1 category can have n posts, cascade all means parent removed all child removed, 
	//parent include all child included- remove+persist//fetchtype=Lazy/Eager,lazy-parent removed not child
	//mapped by matlab wo variable jo doosre class mei use kara hai
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
}
