package com.demoProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demoProject.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
	
	User findByUsername(String username);
	Optional<User> findById(Long id);

}
