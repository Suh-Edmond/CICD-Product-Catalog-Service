package com.catalogprojectservice.catalogprojectservice.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.tika.Tika;

import com.catalogprojectservice.catalogprojectservice.model.Product;

public class ProductResponse {

	private Long id;
	private String name;
	private String description;
	private String currency;
	private Integer quantity;
	private BigDecimal price;
	private Long categoryId;
	private String image;
	private final Map<Integer, String> currencies = new HashMap<Integer, String>();
	
	 
	
	/**
	 * 
	 */
	public ProductResponse() {
		super();
		this.currencies.put( 1, "Dollar");
		this.currencies.put(2, "Euro");
		this.currencies.put(3, "CFA");
	}

	/**
	 * set the response object for product
	 * 
	 * @param product
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public ProductResponse convertProductEntityToProductResponse(Product product) {
		String base64 = this.convertImageToBase64(product.getImage());
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setCategoryId(product.getCategory().getId());
		response.setDescription(product.getDescription());
		response.setCurrency(this.currencies.get(product.getCurrency()));
		response.setPrice(product.getPrice());
		response.setQuantity(product.getQuantity());
		response.setImage("data:" + this.getFileExtension(base64) + ";base64," + base64);
		return response;
	}

	/**
	 * convert a product to a product response without image
	 * 
	 * @param product
	 * @return
	 */
	public ProductResponse convertProductEntityWithoutImageToProductResponse(Product product) {
		String base64 = this.convertImageToBase64(product.getImage());
		ProductResponse response = new ProductResponse();
		response.setId(product.getId());
		response.setName(product.getName());
		response.setCategoryId(product.getCategory().getId());
		response.setDescription(product.getDescription());
		response.setCurrency(this.currencies.get(product.getCurrency()));
		response.setPrice(product.getPrice());
		response.setQuantity(product.getQuantity());
		response.setImage("data:" + this.getFileExtension(base64) + ";base64," + base64);
		return response;
	}

	/**
	 * convert a list of product to a list of product response
	 * 
	 * @param products
	 * @return
	 */
	public List<ProductResponse> convertListProductEntitytoListProductResponse(List<Product> products) {

		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		products.stream().forEach(product -> {
			ProductResponse productResponse = new ProductResponse();
			String base64 = this.convertImageToBase64(product.getImage());
			productResponse.setId(product.getId());
			productResponse.setCategoryId(product.getCategory().getId());
			productResponse.setCurrency(this.currencies.get(product.getCurrency()));
			productResponse.setName(product.getName());
			productResponse.setPrice(product.getPrice());
			productResponse.setQuantity(product.getQuantity());
			productResponse.setDescription(product.getDescription());
			productResponse.setImage("data:" + this.getFileExtension(base64) + ";base64," + base64);
			productResponseList.add(productResponse);
		});
		return productResponseList;
	}

	/**
	 * get update product image
	 * 
	 * @param image
	 * @return
	 */
	public String getUpdateProductImage(byte[] image) {
		String base64 = this.convertImageToBase64(image);
		String updatedImage = "data:" + this.getFileExtension(base64) + ";base64," + base64;
		return updatedImage;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the category
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategoryId(Long category) {
		this.categoryId = category;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * get file extension
	 * 
	 * @param base64
	 * @return
	 */
	private String getFileExtension(String base64) {
		byte[] bytebase64 = DatatypeConverter.parseBase64Binary(base64);
		Tika tika = new Tika();
		String fileType = tika.detect(bytebase64);
		return fileType;

	}

	/**
	 * convert image to base64
	 * 
	 * @param image
	 * @return
	 */
	private String convertImageToBase64(byte[] image) {
		String base64Image = Base64.getEncoder().encodeToString(image);
		return base64Image;
	}

}
