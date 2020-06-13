package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Customer;

public interface CustomerDao {

	public List <Customer> findAll();
	
	public Customer findById(int id);
	
	public void saveCustomer(Customer customer);

}
