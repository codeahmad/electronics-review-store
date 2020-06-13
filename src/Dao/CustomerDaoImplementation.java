package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.advanceHibernateSpringProject.Entity.Customer;

@Repository
public class CustomerDaoImplementation implements CustomerDao {

	// Creating session factory object
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Customer> findAll() {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to find all Customers by id
		Query<Customer> query = session.createQuery("select c from Customer c", Customer.class);

		// Adding result to Customer list
		List<Customer> list = query.getResultList();

		return list;
	}

	@Override
	public Customer findById(int id) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		Customer customer;

		// Catching no result exception if nothing returning from database
		try {
			// Creating query to find Customer by id
			Query<Customer> query = session.createQuery("select c from Customer c" 
							+ " where p.id=:customerId",
								Customer.class).setParameter("customerId", id);

			// Adding result to Customer object
			customer = query.getSingleResult();
		} catch (NoResultException noRes) {
			return null;
		}

		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to add Customer to database
		session.saveOrUpdate(customer);

	}


}
