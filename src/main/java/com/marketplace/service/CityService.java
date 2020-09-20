package com.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.model.City;
import com.marketplace.repository.CityRepository;

@Service
public class CityService {

	
	@Autowired
	CityRepository cityRepository;
	
	public List<City> findAllCities(){
		return cityRepository.findAll();
	}
}
