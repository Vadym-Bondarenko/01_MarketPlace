package com.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.model.City;


public interface CityRepository extends JpaRepository<City, Long> {

	
	
}
