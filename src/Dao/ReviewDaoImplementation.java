package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.advanceHibernateSpringProject.Entity.Review;

@Repository
public class ReviewDaoImplementation implements ReviewDao {

	// Creating session factory object
	@Autowired
	private SessionFactory factory;

	@Override
	public List<Review> findAll() {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to find all Reviews by id
		Query<Review> query = session.createQuery("select p from Review p", Review.class);

		// Adding result to Review list
		List<Review> list = query.getResultList();

		return list;
	}

	@Override
	public Review findById(int id) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		Review review;

		// Catching no result exception if nothing returning from database
		try {
			// Creating query to find Review by id
			Query<Review> query = session.createQuery("select p from Review p" 
							+ " where p.id=:reviewId",
								Review.class).setParameter("reviewId", id);

			// Adding result to Review object
			review = query.getSingleResult();
			
		} catch (NoResultException noRes) {
			return null;
		}

		return review;
	}

	@Override
	public void saveReview(Review review) {

		// Creating session object and getting current session from the factory
		Session session = factory.getCurrentSession();

		// Creating query to add Review to database
		session.save(review);

	}
}
