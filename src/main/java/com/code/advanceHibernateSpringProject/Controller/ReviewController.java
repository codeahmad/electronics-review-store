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

import com.code.advanceHibernateSpringProject.Entity.Product;
import com.code.advanceHibernateSpringProject.Entity.Review;
import com.code.advanceHibernateSpringProject.Service.ProductService;
import com.code.advanceHibernateSpringProject.Service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	// Adding preprocessor for trimming white spaces leading and trailing
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// Creating product service object
	@Autowired
	private ReviewService reviewService;

	// Creating product service object
	@Autowired
	@Qualifier("productServiceImplementation")
	private ProductService productService;

	@GetMapping("/addReview")
	public String addREview(@RequestParam("productId") int id, Model model) {

		// Creating Review object and adding to model
		Review review = new Review();
		model.addAttribute("reviewModel", review);

		// Adding id to the model coming from request param
		model.addAttribute("idModel", id);
		return "add-review-form";
	}

	@PostMapping("/saveReview")
	public String saveReview(@RequestParam("productId") int id, @Valid @ModelAttribute("reviewModel") Review review,
			BindingResult bindingResult, Model model) {

		// Getting product by id
		Product product = productService.findById(id);

		// If validation failed return to add review form with product id model
 		if (bindingResult.hasErrors()) {
			model.addAttribute("idModel", id);
			return "add-review-form";
		} else {

			// If validation passed add review to product and set product to product found by id
			product.addReview(review);
			review.setProduct(product);

			// Saving product and review using product service and customer service
			reviewService.saveReview(review);
			productService.saveProduct(product);
			return "redirect:/list";
		}
	}
}