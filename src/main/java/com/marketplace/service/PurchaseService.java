package com.marketplace.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketplace.model.Product;
import com.marketplace.model.Purchase;
import com.marketplace.model.UserEnt;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.PurchaseRepository;


@Service
public class PurchaseService   {

	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
ProductService productService;	
	public Purchase insert(Purchase p, UserEnt u) {
		p.setBuyer(u);
		return purchaseRepository.save(p);
	}
	
	public Purchase insert(Purchase p) {
	
		return purchaseRepository.save(p);
	}
	
	public Product addProductsPurchase(Product p, Purchase purchase) {
		p.setPurchase(purchase);
		return productService.editProduct(p);
	}
	
	public Purchase findById(Long id) {
		return purchaseRepository.findById(id).orElse(null);
	}
	
	public List<Purchase> findAllPurchase(){
		return purchaseRepository.findAll();
	}

	public List<Purchase> byProprietor(UserEnt u){
		return purchaseRepository.findByBuyer(u);
	}

}
