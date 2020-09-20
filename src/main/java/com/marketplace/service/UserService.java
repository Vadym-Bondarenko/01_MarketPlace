package com.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marketplace.model.UserEnt;
import com.marketplace.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	//cuando almacenemos un usuario en la bbdd hay que encriptar
BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserEnt register(UserEnt u) {
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}
	
	
	public UserEnt findById(long id) {
		return userRepository.findById(id).orElse(null);
		
	}
	
	
	public UserEnt findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}
}
