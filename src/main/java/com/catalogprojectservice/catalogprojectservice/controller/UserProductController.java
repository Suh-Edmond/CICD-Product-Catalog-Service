package com.catalogprojectservice.catalogprojectservice.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.request.ProductRequest;
import com.catalogprojectservice.catalogprojectservice.request.UpdatedProductRequest;
import com.catalogprojectservice.catalogprojectservice.response.ProductResponse;
import com.catalogprojectservice.catalogprojectservice.response.SuccessResponse;
import com.catalogprojectservice.catalogprojectservice.service.UserProductService;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class UserProductController {

	@Autowired
	private UserProductService userProductService;

	private ProductRequest productRequest;
	private ProductResponse productResponse;
	private UpdatedProductRequest updatedProductRequest;

	/**
	 * @param productRequest
	 */
	public UserProductController() {
		super();
		this.productRequest = new ProductRequest();
		this.productResponse = new ProductResponse();
		this.updatedProductRequest = new UpdatedProductRequest();
	}

	
	/**
	 * get all user products
	 * @return
	 */
	@GetMapping("/products-list")
	public List<ProductResponse> getAllUserProducts() {
		return this.productResponse
				.convertListProductEntitytoListProductResponse(userProductService.getAllUserProducts());
	}

	/**
	 * 
	 * create product
	 * 
	 * @param request
	 * @param categoryId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/products/categories/{categoryId}")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequest request,
			@PathVariable Long categoryId) {
		Product savedProduct = userProductService
				.createProduct(this.productRequest.convertProductRequestToProductModel(request), categoryId);

		return new ResponseEntity<Object>(
				(new SuccessResponse(new Date(), "" + savedProduct.getId())),
				HttpStatus.CREATED);
	}

	/**
	 * upload product image
	 * 
	 * @param image
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@PostMapping(path = "/products/{id}/upload-image")
	public ResponseEntity<Object> uploadProductImage(@Valid @RequestParam("image") MultipartFile image, @PathVariable Long id)
			throws IOException {
		userProductService.saveProductImageDB(id, image);
		return new ResponseEntity<Object>((new SuccessResponse(new Date(), "Image upload successfull")), HttpStatus.OK);
	}

	/**
	 * get all user products by category
	 * 
	 * @param id
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/products/categories/{categoryId}")
	public List<ProductResponse> getUserProductsByCategory(@PathVariable Long categoryId) {
		return this.productResponse.convertListProductEntitytoListProductResponse(
				userProductService.getUserProductsByCategory(categoryId));

	}

	/**
	 * get a specific user product by category
	 * 
	 * @param id
	 * @param productId
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/products/{productId}/categories/{categoryId}")
	public ResponseEntity<ProductResponse> getUserProductByCategory(@PathVariable Long productId,
			@PathVariable Long categoryId) {
		Product product = userProductService.getUserProductByCategory(categoryId, productId);
		ProductResponse productResponse = this.productResponse.convertProductEntityToProductResponse(product);
		return ResponseEntity.ok().body(productResponse);
	}

	/**
	 * updated user product
	 * 
	 * @param request
	 * @param id
	 * @param productId
	 * @param categoryId
	 * @return
	 */
	@PutMapping("/products/{productId}/categories/{categoryId}")
	public ResponseEntity<ProductResponse> editUserProduct(@Valid @RequestBody UpdatedProductRequest request,
			@PathVariable Long productId, @PathVariable Long categoryId) {
		Product product = this.updatedProductRequest.convertUpdatedProductRequestToProductModel(request);
		Product updatedProduct = userProductService.updateUserProduct(product, categoryId, productId);
		ProductResponse response = this.productResponse
				.convertProductEntityWithoutImageToProductResponse(updatedProduct);
		return ResponseEntity.accepted().body(response);
	}

	/**
	 * update product image
	 * 
	 * @param image
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/products/{id}/update-image")
	public ResponseEntity<String> editUserProductImage(@Valid @RequestBody MultipartFile image, @PathVariable Long id)
			throws IOException {
		byte[] updatedProductImage = userProductService.updateUserProductImage(id, image);
		String updatedProductImageBase64 = this.productResponse.getUpdateProductImage(updatedProductImage);
		return ResponseEntity.accepted().body(updatedProductImageBase64);
	}

	/**
	 * delete user products
	 * 
	 * @param id
	 * @param productId
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping("/products/{productId}/categories/{categoryId}")
	public ResponseEntity<Object> deleteUserProduct(@PathVariable Long productId, @PathVariable Long categoryId) {
		userProductService.deleteUserProduct(categoryId, productId);
		return new ResponseEntity<Object>((new SuccessResponse()), HttpStatus.NO_CONTENT);
	}

}
