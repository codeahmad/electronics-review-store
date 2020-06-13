package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Product;

public interface ProductDao {

	public List <Product> findAll();
	
	public Product findById(int id);
	
	public void saveProduct(Product product);
	
	public void deleteById(int id);


	

	
}
