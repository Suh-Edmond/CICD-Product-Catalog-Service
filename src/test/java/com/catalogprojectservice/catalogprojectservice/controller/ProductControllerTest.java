package com.catalogprojectservice.catalogprojectservice.controller;

import com.catalogprojectservice.catalogprojectservice.model.Product;
import com.catalogprojectservice.catalogprojectservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService mockProductService;

    @InjectMocks
    private ProductController productController;

    @Test
    void getAllProducts_calls_product_service() {
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
        verify(mockProductService).getProducts();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void getAllCategoryProducts_calls_product_service() {
        Long categoryId = 12345678910L;
        ResponseEntity<List<Product>> responseEntity = productController.getAllCategoryProducts(categoryId);
        verify(mockProductService).getCategoryProducts(categoryId);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void getCategoryProduct_calls_product_service() {
        Long categoryId = 12345678910L;
        Long productId = 145678910L;
        ResponseEntity<Product> responseEntity = productController.getCategoryProduct(productId, categoryId);
        verify(mockProductService).getProduct(categoryId, productId);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}