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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message="Can not be null")
	@Size(min=3, message="Please enter atleast 3 character")
	@Column(name = "name")
	private String name;
	
	// Many customer can have many product and in a bi directional relationship
	// If customer deleted product will not delete
	@ManyToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
	@JoinTable(
			name="product_customer",
			joinColumns=@JoinColumn(name="customer_id"),
			inverseJoinColumns=@JoinColumn(name="product_id"))
	private List <Product> product;

	// Adding convenient method for adding product to customer
	public void addProduct(Product theProduct) {
		if(product==null) {
			product= new ArrayList<>();
		}
		product.add(theProduct);
	}
	
	// Creating no argument constructor
	public Customer() {

	}
	
	// Creating constructor using fields
	public Customer(String name) {
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	// Creating toString method
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";

	}
}