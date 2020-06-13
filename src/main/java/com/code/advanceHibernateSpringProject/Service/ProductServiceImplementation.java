package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.advanceHibernateSpringProject.Dao.ProductDao;
import com.code.advanceHibernateSpringProject.Entity.Product;

@Service
public class ProductServiceImplementation implements ProductService {

	// Creating dao object
	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional
	public List<Product> findAll() {
		
		// Calling dao to find all
		return productDao.findAll();
	}

	@Override
	@Transactional
	public Product findById(int id) {
		
		// calling dao to find product by id
		return productDao.findById(id);
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
	
		// Calling dao to save product to database
		productDao.saveProduct(product);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		productDao.deleteById(id);
		
	}

	

}
