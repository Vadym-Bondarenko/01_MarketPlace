package com.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marketplace.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("select c from Category c where c.featured=true")
	public List<Category> findFeatured();
	
}
