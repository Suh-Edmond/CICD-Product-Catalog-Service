package com.catalogprojectservice.catalogprojectservice.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.catalogprojectservice.catalogprojectservice.model.Product;

public class ProductRequest {

	@NotNull
	@NotBlank(message = "name must not be null")
	@Size(max = 70)
	private String name;

	@NotNull
	@NotBlank(message = "description must not be null")
	private String description;

	@NotNull
	@Min(1)
	@Max(100000)
	private Integer quantity;

	@NotNull
	@DecimalMin(value = "1.0")
	@DecimalMax(value = "1000000.00")
	private BigDecimal price;

	@NotNull
	@Max(3)
	private int currency;

	/**
	 * @param name
	 * @param description
	 * @param quantity
	 * @param price
	 * @param currency
	 */
	public ProductRequest(String name, String description, Integer quantity, BigDecimal price, int currency) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.currency = currency;
	}

	/**
	 * 
	 */
	public ProductRequest() {
		super();
	}

	public Product convertProductRequestToProductModel(ProductRequest request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setQuantity(request.getQuantity());
		product.setPrice(request.getPrice());
		product.setCurrency(request.getCurrency());

		return product;
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

	/**
	 * @return the currency
	 */
	public int getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
	}

}
