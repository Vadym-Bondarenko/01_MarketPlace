package com.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.model.Category;
import com.marketplace.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAllCat(){
		return categoryRepository.findAll();
	}
	
//	public Category findById(Long id) {
//		return categoryRepository.findById(id).orElse(null);
//	
//	}
	
	
}
