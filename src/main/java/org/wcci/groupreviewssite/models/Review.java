package org.wcci.groupreviewssite.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;
	private String contactInfo;
	private String imageUrl;

	@ManyToMany(mappedBy = "reviews")
<<<<<<< HEAD
	private List<Tag> tags;

	@OneToMany(mappedBy = "review")
	private List<Comment> comments;
	
	
	public List<Comment> getComment(){
		return comments;
	}
	
=======
	private Collection<Category> categories;


	@ManyToMany(mappedBy = "reviews")
	private Collection<Tag> tags;

>>>>>>> master
	protected Review() {
	}

	@Override
	public String toString() {
		return "Review [name=" + name + "]";
	}

	public Review(String name, String description, String contactInfo, String imageUrl, Category...categories) {
		this.name = name;
		this.description = description;
		this.contactInfo = contactInfo;
		this.imageUrl = imageUrl;
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

	public Collection <Category> getCategories() {
		return categories;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
