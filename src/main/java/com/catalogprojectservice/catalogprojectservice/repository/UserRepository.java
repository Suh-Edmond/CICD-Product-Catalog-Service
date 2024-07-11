package com.catalogprojectservice.catalogprojectservice.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogprojectservice.catalogprojectservice.model.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

	

	
	
}
