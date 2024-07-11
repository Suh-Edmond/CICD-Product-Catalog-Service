package com.catalogprojectservice.catalogprojectservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogprojectservice.catalogprojectservice.exception.ForbiddenException;
import com.catalogprojectservice.catalogprojectservice.exception.NotFoundException;
import com.catalogprojectservice.catalogprojectservice.model.Category;
import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.repository.CategoryRepository;
import com.catalogprojectservice.catalogprojectservice.repository.UserRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repo;
	@Autowired
	UserRepository userRep;
	@Autowired
	UserService userService;

	public void createCategory(Category category) {
		User authUser = userService.getUserDetails();
		category.setUser(authUser);
		repo.save(category);

	}

	public List<Category> getAllCategories() {
		if (repo.findAll().isEmpty()) {
			throw new NotFoundException("Resource not found");
		}
		return repo.findAll();

	}

	public Category getUserCategory(Long categoryId) {
		User authUser = userService.getUserDetails();
		Optional<Category> category = repo.findAll().stream()
				.filter(cat -> cat.getId().equals(categoryId) && cat.getUser().getId().equals(authUser.getId()))
				.findFirst();
		category.orElseThrow(() -> new NotFoundException("Resource not Found"));
		return category.get();
	}

	public List<Category> retrieveusercategories() {
		User authUser = userService.getUserDetails();
		if (authUser.getCategory().isEmpty()) {
			throw new NotFoundException("Resource not found");
		}
		return authUser.getCategory();

	}

	public void deleteCategory(Long categoryId) {
		User authUser = userService.getUserDetails();
		Optional<Category> deleted = repo.findAll().stream().filter(
				category -> category.getId().equals(categoryId) && category.getUser().getId().equals(authUser.getId()))
				.findFirst();
		deleted.orElseThrow(() -> new ForbiddenException("Access denied to delete this resource"));
		repo.delete(deleted.get());
	}

	public Category updateCategory(Category updatedCategoryRequest, Long categoryId) {
		User authUser = userService.getUserDetails();
		Optional<Category> updatedCategory = repo.findAll().stream().filter(
				category -> category.getId().equals(categoryId) && category.getUser().getId().equals(authUser.getId()))
				.findFirst();
		updatedCategory.orElseThrow(() -> new ForbiddenException("Access denied to update this resource"));
		updatedCategory.get().setName(updatedCategoryRequest.getName());
		repo.save(updatedCategory.get());
		return updatedCategory.get();
	}

}
