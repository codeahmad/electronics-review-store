package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import com.code.advanceHibernateSpringProject.Entity.Review;

public interface ReviewService {
	
	public List<Review> findAll();

	public Review findById(int id);
	
	public void saveReview(Review review);
}
