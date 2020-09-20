package com.marketplace.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marketplace.model.Product;
import com.marketplace.model.Purchase;
import com.marketplace.model.UserEnt;
import com.marketplace.service.ProductService;
import com.marketplace.service.PurchaseService;
import com.marketplace.service.UserService;


@Controller
@RequestMapping("/app")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	ProductService productService;	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	private UserEnt userEnt;
	
	
	//sin crear
	@ModelAttribute("cart")
	public List<Product> productCart(){
		List<Long> content = (List<Long>) session.getAttribute("cart");
		return (content==null) ? null: productService.findAllsByIds(content);

	}
	
	@ModelAttribute("total_cart")
	public Double totalCart() {
		List<Product> productCart= productCart();
		if(productCart !=null)
			return productCart.stream()
					.mapToDouble(p->p.getPrice())
					.sum();
		return 0.0;
	}
	
	
	@ModelAttribute("mypurchase")
	public List<Purchase> myPurchase(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		userEnt= userService.findByEmail(email);
		return purchaseService.byProprietor(userEnt);
	}
	
	@GetMapping("/shoppingcart")
	public String seeCart(Model model) {
		return "app/purchase/shoppingcart";
	}
	
	

	@GetMapping("/shoppingcart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long> content= (List<Long>) session.getAttribute("cart");
	if(content == null)
		content= new ArrayList<>();
		if(!content.contains(id))
			content.add(id);
		session.setAttribute("cart", content);
		return "redirect:/app/shoppingcart";
	}
	
	@GetMapping("/shoppingcart/delete/{id}")
	public String deleteFromShoppingcart(Model model, @PathVariable Long id) {
		
		List<Long> content=(List<Long>) session.getAttribute("cart");
		if(content == null)
			return "redirect:/public";
		content.remove(id);
		if(content.isEmpty())
			session.removeAttribute("cart");
		else
			session.setAttribute("cart", content);
		return "redirect:/app/shoppingcart";
	}
	

	@GetMapping("shoppingcart/finalize")
	public String checkout() {
		List<Long> content = (List<Long>) session.getAttribute("cart");
		if (content == null)
			return "redirect:/public";
		
		List<Product> products = productCart();
		
		Purchase purchase = purchaseService.insert(new Purchase(), userEnt);
		
		products.forEach(p-> purchaseService.addProductsPurchase(p, purchase));
		session.removeAttribute("cart");
		
		return "redirect:/app/purchase/bill/"+purchase.getId();
		
	}
	
	
	
	
	@GetMapping("/purchase/bill/{id}")
	public String bill(Model model, @PathVariable Long id) {
		 Purchase purchase = purchaseService.findById(id);
		 List<Product> products = productService.productoPurchase(purchase);
		 model.addAttribute("products", products);
		 model.addAttribute("purchase", purchase);
		 model.addAttribute("total_purchase", products.stream().mapToDouble(p-> p.getPrice()).sum());
		 return "/app/purchase/bill";
	}
	@GetMapping("/mypurchase")
	public String getMyPurchase(Model model) {
		return "/app/purchase/list";
	}
	
}
