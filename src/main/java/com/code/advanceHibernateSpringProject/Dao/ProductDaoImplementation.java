package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.advanceHibernateSpringProject.Entity.Product;

@Repository
public class ProductDaoImplementation implements ProductDao {

	// Creating session factory object
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Product> findAll() {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to find all products by id
		Query<Product> query = session.createQuery("select p from Product p", Product.class);

		// Adding result to product list
		List<Product> list = query.getResultList();

		return list;
	}

	@Override
	public Product findById(int id) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();


		
		// Catching no result exception if nothing retuening from database
		
			// Creating query to find product by id
			// Join fetch with customer to load lazy data in memory
			Query<Product> query = session.createQuery("select p from Product p" 
							+ " LEFT JOIN FETCH p.customer"
							+ " where p.id=:productId",
								Product.class).setParameter("productId", id);
			
			// Adding result to product object
		 	Product product = query.getSingleResult();


			
			// Join fetch with customer to load lazy data in memory 
			// To avoid multiple bags exception used two separate query
			query = session.createQuery("select p from Product p" 
					+ " LEFT JOIN FETCH p.review"
					+ " where p.id=:productId",
						Product.class).setParameter("productId", id);

			// Adding result to product object
			product = query.getSingleResult();
			
			

		return product;
	}

	@Override
	public void saveProduct(Product product) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to add product to database
		session.saveOrUpdate(product);

	}

	@Override
	public void deleteById(int id) {
		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();
		
		Query query =  session.createQuery("delete from Product where id=:productId");
			
			query.setParameter("productId", id);

			query.executeUpdate();
	}

	
}