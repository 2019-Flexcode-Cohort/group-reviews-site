package org.wcci.groupreviewssite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String contactInfo;
	private String imageUrl;
	@ManyToOne
	private Category category;
	
	protected Review() {}
	public Review(String name, String description, String contactInfo, String imageUrl, Category category) {
		this.name = name;
		this.description = description;
		this.contactInfo = contactInfo;
		this.imageUrl = imageUrl;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public Long getId() {
		return id;
	}

}
