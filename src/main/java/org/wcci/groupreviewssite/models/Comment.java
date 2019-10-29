package org.wcci.groupreviewssite.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	private String description;

	
	@ManyToOne
	private Review review;
	
	public String getDescription() {
		return description;
	}

	
	
	public Comment(String description, Review review) {
		this.description = description;
		this.review = review;
	}

	protected Comment() {
	
	}
	
	

}
