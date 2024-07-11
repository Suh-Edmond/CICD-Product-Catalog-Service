package com.catalogprojectservice.catalogprojectservice.controller;

import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping()
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok((productService.getProducts()));
	}


	@GetMapping("/categories")
	public ResponseEntity<List<Product>> getAllCategoryProducts(@RequestParam("categoryId") Long categoryId) {
		return ResponseEntity.ok(productService.getCategoryProducts(categoryId));
	}

	@GetMapping("{id}/categories")
	public ResponseEntity<Product> getCategoryProduct(@PathVariable Long id, @RequestParam("categoryId") Long categoryId) {
		return  ResponseEntity.ok(productService.getProduct(categoryId, id));
	}

}
