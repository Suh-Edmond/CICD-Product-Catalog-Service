package com.catalogprojectservice.catalogprojectservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.catalogprojectservice.catalogprojectservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value ="SELECT * FROM product WHERE  name LIKE CONCAT('%', :name, '%');", nativeQuery=true)
	List<Product> findByName(@Param("name") String name);
	
}
