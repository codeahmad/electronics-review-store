package com.code.advanceHibernateSpringProject.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message="Can not be null")
	@Size(min=2, message="Please enter atleast 2 character")
	@Column(name = "name")
	private String name;
	
	// Many products can have many customers and in a bi directional relationship
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="product_customer",
	joinColumns=@JoinColumn(name="product_id"),
	inverseJoinColumns=@JoinColumn(name="customer_id"))
	private List <Customer> customer;
		
	// One product can have many reviews
	// Mapped by product variable in review class
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List <Review> review;
	
	// Adding convenient method for adding customer to product
	public void addCustomer(Customer theCustomer) {
		if (customer==null) {
			customer = new ArrayList<>();
		}
		customer.add(theCustomer);
	}
	
	// Adding convenient method for adding reviews to the product
	public void addReview(Review theReview) {
		if(review==null) {
			review= new ArrayList<>();
		}
		review.add(theReview);
	}
	
	// Creating no argument constructor
	public Product() {

	}

	// Creating constructor using fields
	public Product(String name) {
		this.name = name;
	}

	// Creating getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List <Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List <Customer> customer) {
		this.customer = customer;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	// Creating toString method
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";

	}
}