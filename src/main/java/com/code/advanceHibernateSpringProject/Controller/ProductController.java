package com.code.advanceHibernateSpringProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.advanceHibernateSpringProject.Entity.Customer;
import com.code.advanceHibernateSpringProject.Entity.Product;
import com.code.advanceHibernateSpringProject.Entity.Review;
import com.code.advanceHibernateSpringProject.Service.CustomerService;
import com.code.advanceHibernateSpringProject.Service.ProductService;

@Controller
public class ProductController {

	// Adding preprocessor for trimming white spaces leading and trailing
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// Creating customer service object
	@Autowired
	@Qualifier("customerServiceImplementation")
	private CustomerService customerService;

	// Creating product service object
	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public String homePage(Model model) {

		// Creating product object list and adding items from service
		List<Product> list = productService.findAll();

		// Adding list to the model
		model.addAttribute("productModel", list);

		return "home-page";
	}

	@GetMapping("/addProduct")
	public String addProduct(Model model) {

		// Creating product object and adding to model
		Product product = new Product();
		model.addAttribute("productModel", product);
		return "add-product-form";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@Valid @ModelAttribute("productModel") Product product,
								BindingResult bindingResult) {
		
		// If validation fails return to add product form
		if (bindingResult.hasErrors()) {
			return "add-product-form";
		} else {
			
			// If validation pass Save product using product service
			productService.saveProduct(product);

			return "redirect:/list";
		}
	}

	@GetMapping("/customerAndReviewPage")
	public String customerAndReview(@RequestParam("productId") int id, Model model) {

		// Getting product by id and Adding product to the model
		Product product = productService.findById(id); 
		model.addAttribute("productModel", product);

		// Getting list of customers from the product
		List<Customer> customerList = product.getCustomer();

		// Adding customer list to the model
		model.addAttribute("customerModel", customerList);

		// Getting List of reviews from the product
		List<Review> reviewList = product.getReview();

		// Adding review list to the model
		model.addAttribute("reviewModel", reviewList);
		return "customer-review";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("productId") int id) {

		// Calling product service to delete product by id
		productService.deleteById(id);

		return "redirect:/list";
	}

	@GetMapping("/updateProduct")
	public String updateProduct(@RequestParam("productId") int id, Model model) {
		
		// Getting product by id
		Product product = productService.findById(id);
		
		// Adding product to the model
		model.addAttribute("productModel", product);

		return "add-product-form";
	}

}
