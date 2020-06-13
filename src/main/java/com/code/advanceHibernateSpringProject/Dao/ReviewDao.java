package com.code.advanceHibernateSpringProject.Dao;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Review;

public interface ReviewDao {

	public List <Review> findAll();
	
	public Review findById(int id);
	
	public void saveReview(Review review);
	
}
