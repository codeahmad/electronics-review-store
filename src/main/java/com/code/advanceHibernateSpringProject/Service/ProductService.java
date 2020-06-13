package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Product;

public interface ProductService {
	
	public List<Product> findAll();

	public Product findById(int id);
	
	public void saveProduct(Product product);

	public void deleteById(int id);

}
