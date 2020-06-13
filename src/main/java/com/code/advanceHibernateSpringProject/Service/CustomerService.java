package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Customer;
import com.code.advanceHibernateSpringProject.Entity.Product;

public interface CustomerService {
	
	public List<Customer> findAll();

	public Customer findById(int id);
	
	public void saveCustomer(Customer customer);

}
