package com.catalogprojectservice.catalogprojectservice.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.catalogprojectservice.catalogprojectservice.model.Category;

public class CategoryRequest {

	@NotBlank(message="null")
	@NotNull(message="name must not be null")
	@Size(min=3,max=50)
	private String name;

	public Category convertCategoryRequestToCategoryModel(CategoryRequest catrequest) {
		Category category=new Category();
		category.setName(catrequest.getName());
		return category;
		
	}
	public CategoryRequest(String name) {
		super();
		this.name = name;
	}

	public CategoryRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
