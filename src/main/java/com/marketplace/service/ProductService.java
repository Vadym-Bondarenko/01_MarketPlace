package com.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marketplace.model.Category;
import com.marketplace.model.Product;
import com.marketplace.model.Purchase;
import com.marketplace.model.UserEnt;
import com.marketplace.repository.ProductRepository;
import com.marketplace.upload.StorageService;


@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StorageService storageService;
	public Product addProduct(Product p) {
		return productRepository.save(p);
		
	}
	
	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}
	
	public void deleteProduct(Product p) {
		if(!p.getImage().isEmpty()){
			storageService.delete(p.getImage());
			productRepository.delete(p);
		}
	}
	public Product editProduct(Product p) {
		return productRepository.save(p);
	}
	public Product findById(long id) {
		return productRepository.findById(id).orElse(null);
		
	}
	
	public List<Product> ownProducts(UserEnt u){
		return productRepository.findBySeller(u);
	}
	
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}
	
		

	public Page<Product> findAllPaginated(Pageable pageable) {
		return productRepository.findAll(pageable);
	}	
	
	public List<Product> unsoldProducts(){
		return productRepository.findByPurchaseIsNull();
	}
	
	public  List<Product> searchProduct(String query){
		return productRepository.findByTitleContainsIgnoreCaseAndPurchaseIsNull(query);
		//return productRepository.findByTitleContainsIgnoreCaseAndPurchaseIsNull(query);
	}
	public  List<Product> SearchmyProducts(String query, UserEnt seller){
	
return productRepository.findByTitleContainsIgnoreCaseAndSeller(query, seller);
	}

	public List<Product> findAllByCategory(Category category){
		return productRepository.findByCategory(category);
	}
	
	public List<Product> findAllByCategory(Long Idcategory){
		return productRepository.findByCategoryIdAndPurchaseIsNull(Idcategory);
	}

public List<Product> findAllsByIds(List<Long> ids){
	return productRepository.findAllById(ids);
}

public List<Product> productoPurchase(Purchase p) {
	return productRepository.findByPurchase(p);
	
}

}
