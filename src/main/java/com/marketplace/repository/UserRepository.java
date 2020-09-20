package com.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketplace.model.UserEnt;


@Repository
public interface UserRepository extends JpaRepository<UserEnt,Long>{
	//nos devuelve el primer por producto por email

	UserEnt findFirstByEmail(String email);
}
