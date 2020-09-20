package com.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketplace.model.Category;
import com.marketplace.model.Product;
import com.marketplace.model.Purchase;
import com.marketplace.model.UserEnt;

public interface ProductRepository extends JpaRepository<Product, Long>{

	
public List<Product> findByCategory(Category category);

public List<Product> findBySeller(UserEnt seller);

List<Product> findByPurchase(Purchase purchase);


List<Product> findByTitleContainsIgnoreCaseAndPurchaseIsNull(String title);
List<Product> findByPurchaseIsNull();

List<Product>findByTitleContainsIgnoreCaseAndSeller(String title, UserEnt seller);

List<Product> findByCategoryId(Long idCategory);


List<Product> findByCategoryIdAndPurchaseIsNull(Long  idCategory);
}
