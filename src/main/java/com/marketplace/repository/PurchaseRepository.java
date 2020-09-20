package com.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.marketplace.model.Purchase;
import com.marketplace.model.UserEnt;

@Service

public interface PurchaseRepository extends JpaRepository<Purchase,	Long>{

	List<Purchase> findByBuyer(UserEnt user);
}
