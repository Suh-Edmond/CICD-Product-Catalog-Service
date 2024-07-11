package com.catalogprojectservice.catalogprojectservice.request;

import java.math.BigDecimal;
import java.sql.Blob;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.catalogprojectservice.catalogprojectservice.model.Product;

public class UpdatedProductRequest {

	@NotBlank(message = "description must not be null")
	@Size(max = 70)
	private String name;
	
	@NotBlank(message = "description must not be null")
	private String description;
	
	@Min(1)
	@Max(100000)
	private Integer quantity;
	
	@DecimalMin(value = "1.0")
	@DecimalMax(value="1000000.00")
	private BigDecimal price;

	/**
	 * @param name
	 * @param description
	 * @param quantity
	 * @param price
	 * @param image
	 */
	public UpdatedProductRequest(String name, String description, Integer quantity, BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		
	}

	/**
	 * convert a product request to a product model
	 * @param request
	 * @return
	 */
	public Product convertUpdatedProductRequestToProductModel(UpdatedProductRequest request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setQuantity(request.getQuantity());
		product.setPrice(request.getPrice());
		return product;
	}

	/**
	 * 
	 */
	public UpdatedProductRequest() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	

}
