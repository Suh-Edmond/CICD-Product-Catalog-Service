package com.catalogprojectservice.catalogprojectservice.response;

import java.util.ArrayList;
import java.util.List;

import com.catalogprojectservice.catalogprojectservice.model.Category;
import com.catalogprojectservice.catalogprojectservice.model.Product;

public class CategoryResponse {

	private Long id;
	private String name;
	private List<ProductResponse> products;
	public CategoryResponse(Long id, String name, List<ProductResponse> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}
	
	public CategoryResponse convertCategoryModelToResponse(Category category) {
        ProductResponse proresponse= new ProductResponse();
        CategoryResponse response= new CategoryResponse();
        List<ProductResponse> res=proresponse.convertListProductEntitytoListProductResponse(category.getProducts());
		response.setId(category.getId());
		response.setName(category.getName());
		response.setProducts(res);
		return response;
		
	}
	public List<CategoryResponse> convertCategoryListModelToCategoryListResponse(List<Category> category) {
	 List<CategoryResponse> categoryResponse= new ArrayList<CategoryResponse>();
     for(Category cate : category) {
    	 CategoryResponse response = this.convertCategoryModelToResponse(cate);
    	 categoryResponse.add(response);
     }
     return categoryResponse;
	}
	
	public CategoryResponse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductResponse> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	
	
	
}
