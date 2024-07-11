package com.catalogprojectservice.catalogprojectservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catalogprojectservice.catalogprojectservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	

	

}
