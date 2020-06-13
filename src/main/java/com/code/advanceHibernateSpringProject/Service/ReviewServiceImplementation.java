package com.code.advanceHibernateSpringProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.advanceHibernateSpringProject.Dao.ReviewDao;
import com.code.advanceHibernateSpringProject.Entity.Review;

@Service
public class ReviewServiceImplementation implements ReviewService {

	// Creating dao object
	@Autowired
	private ReviewDao ReviewDao;
	
	@Override
	@Transactional
	public List<Review> findAll() {
		
		// Calling dao to find all
		return ReviewDao.findAll();
	}

	@Override
	@Transactional
	public Review findById(int id) {
		
		// calling dao to find Review by id
		return ReviewDao.findById(id);
	}

	@Override
	@Transactional
	public void saveReview(Review review) {
	
		// Calling dao to save Review to database
		ReviewDao.saveReview(review);
	}

}
