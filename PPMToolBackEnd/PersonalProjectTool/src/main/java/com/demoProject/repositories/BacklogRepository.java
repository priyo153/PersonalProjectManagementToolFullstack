package com.demoProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demoProject.entities.Backlog;



@Repository
public interface BacklogRepository extends JpaRepository<Backlog,Long> {
	
	Backlog findByProjectIdentifier(String identifier);

}
