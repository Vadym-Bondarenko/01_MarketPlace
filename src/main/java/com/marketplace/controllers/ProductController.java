package com.marketplace.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.marketplace.model.Product;
import com.marketplace.model.UserEnt;
import com.marketplace.service.CategoryService;
import com.marketplace.service.CityService;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import com.marketplace.upload.StorageService;



@Controller
@RequestMapping("/app")

public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	private UserEnt user;
	

	@ModelAttribute("category")
	public List<Product> listCategories(){
		
		return productService.unsoldProducts();
	}

	
	@ModelAttribute("myproducts")
	public List<Product> myProducts(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user=userService.findByEmail(email);
		return productService.ownProducts(user);
	}
	
	@GetMapping("/myproducts")
	public String list(Model model, @RequestParam(name="query", required = false)String query) {
		if(query !=null)
			model.addAttribute("myproducts", productService.SearchmyProducts(query, user));
			model.addAttribute("categories", categoryService.findAllCat());

		return "app/products/list";
	}
	@GetMapping("/myproducts/new")
	public String newProductForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", categoryService.findAllCat());
		model.addAttribute("city", cityService.findAllCities());
		return "app/products/form";
	}
	@PostMapping("/myproducts/new/submit")
	public String newProductSubmit(@ModelAttribute Product product, @RequestParam("file") MultipartFile file)
	{
		if(!file.isEmpty()) {
			String image= storageService.store(file);
			product.setImage(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
			
		}
		
		product.setSeller(user);
		productService.addProduct(product);
		return "redirect:/app/myproducts";
	}
	
	@GetMapping("/myproducts/{id}/delete")
	public String deleteProduct(@PathVariable Long id) {
		Product p = productService.findById(id);
		if(p.getPurchase()==null) {
			productService.deleteProduct(p);
		}
		return "redirect:/app/myproducts";

	}

	@GetMapping("/edit/{id}")
	public String EditProduct(@PathVariable long id, Model model) {
		Product product = productService.findById(id);
		if(product !=null) {
			model.addAttribute("product", product);
			model.addAttribute("category", categoryService.findAllCat());
			model.addAttribute("city", cityService.findAllCities());
			return "app/products/form";
		}
		else {
			return "redirect:/app/products/list";
		}
	}
	
	
	
	
}
