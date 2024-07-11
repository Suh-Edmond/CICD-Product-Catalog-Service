package com.catalogprojectservice.catalogprojectservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogprojectservice.catalogprojectservice.exception.NotFoundException;
import com.catalogprojectservice.catalogprojectservice.model.Category;
import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.repository.CategoryRepository;
import com.catalogprojectservice.catalogprojectservice.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	/**
	 * get all products
	 * 
	 * @return List Product
	 */
	public List<Product> getProducts() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new NotFoundException("No products found");
		}
		return products;
	}

	/**
	 * get all products of a category
	 * 
	 * @param categoryId
	 * @return
	 */
	public List<Product> getCategoryProducts(Long categoryId) {
		Category category = this.getCategory(categoryId);
		List<Product> categoryProducts = productRepository.findAll().stream()
				.filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
		if (categoryProducts.isEmpty()) {
			throw new NotFoundException("No products found under category id " + categoryId);
		}
		return categoryProducts;
	}

	/**
	 * get a specific product
	 * 
	 * @param categoryId
	 * @param id
	 * @return
	 */
	public Product getProduct(Long categoryId, Long id) {
		Category category = this.getCategory(categoryId);
		Optional<Product> product = productRepository.findAll().stream()
				.filter(prod -> prod.getId() == id && prod.getCategory().equals(category)).findFirst();
		product.orElseThrow(() -> new NotFoundException("No product found with id " + id +" for "+" category id "+ categoryId));
		return product.get();
	}

	/**
	 * get a category
	 * 
	 * @param categoryId
	 * @return
	 */
	private Category getCategory(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		category.orElseThrow(() -> new NotFoundException("No category found with id " + categoryId));
		return category.get();
	}

	public List<Product> searchProduct(String name){
		List<Product> searchedProduct = productRepository.findByName(name);
		if(searchedProduct.isEmpty()) {
			throw new NotFoundException("No product(s) found with name like "+ name);
		}
		return searchedProduct;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
