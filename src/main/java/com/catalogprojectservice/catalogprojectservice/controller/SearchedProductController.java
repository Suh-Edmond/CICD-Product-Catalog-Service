package com.catalogprojectservice.catalogprojectservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.response.ProductResponse;
import com.catalogprojectservice.catalogprojectservice.service.ProductService;

@CrossOrigin()
@RestController
public class SearchedProductController {
	
	@Autowired
	private ProductService productService;
	private ProductResponse productResponse;
	
	/**
	 * constructor
	 */
	public  SearchedProductController() {
		this.productResponse = new ProductResponse();
	}
	
	
	
	@GetMapping("/api/all-products")
	public List<ProductResponse> searchProduct(@RequestParam("search") String name) {
		List<Product> products = productService.searchProduct(name);
		return this.productResponse.convertListProductEntitytoListProductResponse(products);
	}

}
