package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.advanceHibernateSpringProject.Dao.CustomerDao;
import com.code.advanceHibernateSpringProject.Entity.Customer;
import com.code.advanceHibernateSpringProject.Entity.Product;

@Service
public class CustomerServiceImplementation implements CustomerService {

	// Creating dao object
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		
		// Calling dao to find all
		return customerDao.findAll();
	}

	@Override
	@Transactional
	public Customer findById(int id) {
		
		// calling dao to find Customer by id
		return customerDao.findById(id);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
	
		// Calling dao to save Customer to database
		customerDao.saveCustomer(customer);
	}

	

}
