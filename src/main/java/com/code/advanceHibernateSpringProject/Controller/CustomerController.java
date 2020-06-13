package com.code.advanceHibernateSpringProject.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.advanceHibernateSpringProject.Entity.Customer;
import com.code.advanceHibernateSpringProject.Entity.Product;
import com.code.advanceHibernateSpringProject.Service.CustomerService;
import com.code.advanceHibernateSpringProject.Service.ProductService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

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
	@Qualifier("productServiceImplementation")
	private ProductService productService;

	@GetMapping("/addCustomer")
	public String addCustomer(@RequestParam("productId") int id, Model model) {

		// Adding product id to model coming as request parameter
		model.addAttribute("idModel", id);

		// Creating Customer object and adding to model
		Customer customer = new Customer();
		model.addAttribute("customerModel", customer);

		return "add-customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestParam("productId") int id,
								@Valid @ModelAttribute("customerModel") Customer customer, 
									BindingResult bindingResult , Model model) {

		// Getting product by id to add customer to it
		Product product = productService.findById(id);
		
		// If fail validation back to add customer form with product id
		if (bindingResult.hasErrors()) {
			model.addAttribute("idModel", id);
			return "add-customer-form";
		} else {

			// If validation pass add customer to product
			product.addCustomer(customer);

			// Calling customer and product service to save customer
			customerService.saveCustomer(customer);
			productService.saveProduct(product);

			return "redirect:/list";
		}
	}
}
