package com.catalogprojectservice.catalogprojectservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.catalogprojectservice.catalogprojectservice.exception.ForbiddenException;
import com.catalogprojectservice.catalogprojectservice.exception.NotFoundException;
import com.catalogprojectservice.catalogprojectservice.model.Category;
import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.model.User;
import com.catalogprojectservice.catalogprojectservice.repository.CategoryRepository;
import com.catalogprojectservice.catalogprojectservice.repository.ProductRepository;
import com.catalogprojectservice.catalogprojectservice.repository.UserRepository;

@Service
public class UserProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	 

	/**
	 * create a product
	 * 
	 * @param prod
	 * @param categoryId
	 */

	public Product createProduct(Product prod, Long categoryId) {
		User currentlyAuthUser = this.getCurrentAuthenticatedUserFromToken();
		Category category = this.verifyUserCategory(categoryId, currentlyAuthUser.getId());
		prod.setCategory(category);
		Product savedProduct = productRepository.saveAndFlush(prod);
		return savedProduct;
	}

	/**
	 * save product image to database
	 *   
	 * @param id
	 * @param image
	 * @throws IOException
	 */
	public void saveProductImageDB(Long id, MultipartFile image) throws IOException {
		Optional<Product> savedProduct = productRepository.findAll().stream()
				.filter(product -> product.getId().equals(id)).findFirst();
		savedProduct.orElseThrow(() -> new NotFoundException("Product Id:" + id + " does not exist in the database"));
		String fileName = image.getOriginalFilename();
		try {
			savedProduct.get().setImage(image.getBytes());
		} catch (IOException e) {
			throw new IOException("Could not read file");
		}
		productRepository.save(savedProduct.get());

	}

	/**
	 * get all user products by category
	 * 
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	public List<Product> getUserProductsByCategory(Long categoryId) {
		User currentlyAuthUser = this.getCurrentAuthenticatedUserFromToken();
		Category category = this.verifyUserCategory(categoryId, currentlyAuthUser.getId());
		List<Product> userProducts = productRepository.findAll().stream()
				.filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
		if (userProducts.isEmpty()) {
			throw new NotFoundException("No products found");
		}
		return userProducts;
	}

	/**
	 * verify id the category belong to the user
	 * 
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	private Category verifyUserCategory(Long categoryId, Long userId) {
		Optional<Category> category = categoryRepository.findAll().stream()
				.filter(cat -> cat.getId().equals(categoryId) && cat.getUser().getId().equals(userId)).findFirst();
		category.orElseThrow(
				() -> new ForbiddenException("access denied to this resource"));
		return category.get();
	}
	
	
	
	/**
	 * get currently auth user from the token
	 * @return
	 */
	public User getCurrentAuthenticatedUserFromToken()
	{
		User currentlyAuthUser = userService.getUserDetails();
		return currentlyAuthUser;
	}

	/**
	 * get a specific user product under a category
	 * 
	 * @param categoryId
	 * @param id
	 * @return
	 */
	public Product getUserProductByCategory(Long categoryId, Long id) {
		User authUser = this.getCurrentAuthenticatedUserFromToken();
		Category category = this.verifyUserCategory(categoryId, authUser.getId());
		Optional<Product> product = productRepository.findAll().stream()
				.filter(pro -> pro.getCategory().equals(category) && pro.getId().equals(id)).findFirst();
		product.orElseThrow(() -> new NotFoundException("No product resource found with id:"+id));
		return product.get();
	}

	/**
	 * edit user product
	 * 
	 * @param categoryId
	 * @param id
	 */
	public Product updateUserProduct(Product updatedProduct, Long categoryId, Long id) {
	
		Product product = this.getUserProductByCategory(categoryId, id);
		product.setName(updatedProduct.getName());
		product.setDescription(updatedProduct.getDescription());
		product.setQuantity(updatedProduct.getQuantity());
		product.setPrice(updatedProduct.getPrice());
		productRepository.save(product);

		return product;

	}

	/**
	 * updated product image
	 * 
	 * @param id
	 * @param image
	 * @throws IOException
	 */
	public byte[] updateUserProductImage(Long id, MultipartFile image) throws IOException {
		byte[] updatedProductImage;
		try {
			this.saveProductImageDB(id, image);
			updatedProductImage = productRepository.findById(id).get().getImage();
		} catch (IOException e) {
			throw new IOException("Could not read file content");
		}
		return updatedProductImage;
	}

	/**
	 * delete user category
	 * 
	 * @param categoryId
	 * @param userId
	 * @param id
	 */
	public void deleteUserProduct(Long categoryId, Long id) {
		Product product = this.getUserProductByCategory(categoryId, id);
		productRepository.delete(product);
	}

	/**
	 * get all categories of a user
	 * 
	 * @param userId
	 * @return
	 */
	private List<Category> getUserCategories() {
		User authUser = this.getCurrentAuthenticatedUserFromToken();
		List<Category> userCategories = categoryRepository.findAll().stream()
				.filter(category -> category.getUser().getId().equals(authUser.getId())).collect(Collectors.toList());
		if (userCategories.isEmpty()) {
			throw new NotFoundException("No categories found for user with id " + authUser.getId());
		}
		return userCategories;
	}

	/**
	 * get all user products
	 * 
	 * @param userId
	 * @return
	 */
	public List<Product> getAllUserProducts() {
		List<Category> userCategories = this.getUserCategories();
		List<Product> allUserProducts = new ArrayList<Product>();
		userCategories.stream().forEach(category -> {
			List<Product> productsByCategory = this.getUserProductsByCategory(category.getId());
			allUserProducts.addAll(productsByCategory);
		});

		return allUserProducts;

	}

}
