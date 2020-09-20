package com.marketplace.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketplace.model.Product;
import com.marketplace.service.CategoryService;
import com.marketplace.service.ProductService;


@Controller
@RequestMapping("/public")
public class PublicZoneController {

	
	@Autowired
	ProductService productService;
	
	
	@Autowired
	CategoryService categoryService;
	
	
	
	//nuevo
	
//@GetMapping({"/","/index"})
//public String index(@RequestParam(name="idCategory", required = false) Long idCategory, Model model) {
//public String index(@RequestParam(name="idCategory", required=false) Long idCategory,@RequestParam(name="query", required=false) String query, Model model, Pageable pageable) {
//		
//model.addAttribute("categories", categoryService.findAllCat());
//		
//
//
//		List<Product> products;
	
//	if(idCategory==null) {
//			products=(List<Product>) productService.findAllPaginated(pageable);
//		}
//		else if(query!=null) {
//			products=productService.searchProduct(query);
//		}
//		else {
//			products=productService.findAllByCategory(idCategory);
//			
//		}
//		model.addAttribute("products", productService.findAllPaginated(pageable));
//	}
	
	
	@ModelAttribute("products")
	public List<Product> listProducts(){
		
		return productService.unsoldProducts();
	}

	@ModelAttribute("category")
	public List<Product> listCategories(){
		
		return productService.unsoldProducts();
	}



	
	
	@GetMapping({"/","/index"})
	public String index(@RequestParam(name="idCategory", required=false ) Long idCategory, @RequestParam(name="query", required=false) String query, Model model) {		
		
		
		List<Product> products;
		
		if (idCategory == null) {
			products = productService.unsoldProducts();
		}
		
//		else if(query!=null) {
//			products=productService.searchProduct(query);
//		}
		else {			
			products = productService.findAllByCategory(idCategory);	
		model.addAttribute("products", products);
		}
		
		model.addAttribute("categories", categoryService.findAllCat());

		
		return "index";
	}
	
	@GetMapping("/product/{id}")
	public String showProduct(@PathVariable Long id, Model model) {
		Product result=productService.findById(id);
		if(result !=null) {
			model.addAttribute("product", result);
			model.addAttribute("categories", categoryService.findAllCat());

			return "product";
		}
		
		return "redirect:/public";
	}
	
	

	
	
}
