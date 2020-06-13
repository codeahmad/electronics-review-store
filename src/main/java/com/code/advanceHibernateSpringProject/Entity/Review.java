package com.code.advanceHibernateSpringProject.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="review")
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="Can not be null")
	@Size(min=1, message="Please enter atleast 1 character")
	@Size(max=200, message="Please enter less than 200 characters")
	@Column(name="review_comment")
	private String reviewComment;
	
	
	// Many reviews can have one product, if review is deleted product will not be deleted
	@ManyToOne(fetch=FetchType.EAGER,
			cascade= {CascadeType.REFRESH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH})
	@JoinColumn(name="product_id")
	private Product product;

	
	// Creating no argument constructor
	public Review() {
		
	}

	public void addProduct(Product product) {
		this.product=product;
	}
	// Creating constructor using fields
	public Review(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	// Creating getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	// Creating to String method
	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewComment=" + reviewComment + "]";
	}

}