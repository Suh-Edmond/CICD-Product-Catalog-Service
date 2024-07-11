package com.catalogprojectservice.catalogprojectservice.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogprojectservice.catalogprojectservice.model.Category;
import com.catalogprojectservice.catalogprojectservice.repository.UserRepository;
import com.catalogprojectservice.catalogprojectservice.request.CategoryRequest;
import com.catalogprojectservice.catalogprojectservice.response.CategoryResponse;
import com.catalogprojectservice.catalogprojectservice.response.SuccessResponse;
import com.catalogprojectservice.catalogprojectservice.service.CategoryService;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	UserRepository userRep;
	@Autowired
	CategoryService categoryService;

	private CategoryRequest categoryRequest;
	private CategoryResponse categoryResponse;
	
	
	/**
	 * constructor
	 */
	public CategoryController() {
		this.categoryRequest = new CategoryRequest();
		this.categoryResponse = new CategoryResponse();
	}

	
	/**
	 * create category
	 * @param cat
	 * @return
	 */
	@PostMapping("/categories")
	public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryRequest cat) {
		categoryService.createCategory(this.categoryRequest.convertCategoryRequestToCategoryModel(cat));
		return new ResponseEntity<Object>(new SuccessResponse(new Date(), "Successfully created category"),
				HttpStatus.CREATED);
	}

	/**
	 * get all categories
	 * @return
	 */
	@GetMapping("/category-list")
	public List<CategoryResponse> getAllCategories() {
		return this.categoryResponse.convertCategoryListModelToCategoryListResponse(categoryService.getAllCategories());

	}

	/**
	 * get all user categories
	 * @return
	 */
	@GetMapping("/categories")
	public List<CategoryResponse> retrieveusercategories() {
		 return this.categoryResponse.convertCategoryListModelToCategoryListResponse(categoryService.getAllCategories());

	}

	/**
	 * get specific category
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/categories/{categoryId}")
	public CategoryResponse findOneCategory(@PathVariable Long categoryId) {
		return this.categoryResponse.convertCategoryModelToResponse(categoryService.getUserCategory(categoryId));
	}

	
	/**
	 * delete category
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	
	/**
	 * update category
	 * @param categoryRequest
	 * @param categoryId
	 * @return
	 */
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest,
			@PathVariable Long categoryId) {
		Category request = this.categoryRequest.convertCategoryRequestToCategoryModel(categoryRequest); 
		CategoryResponse response = this.categoryResponse.convertCategoryModelToResponse(categoryService.updateCategory(request, categoryId));
		return ResponseEntity.accepted().body(response);
	}

}
