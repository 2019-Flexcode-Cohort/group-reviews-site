package org.wcci.groupreviewssite;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reviews {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	private String contactInfo;
	
	public Reviews() {
	}
	public long getId() {
		return id;
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
	
	public Reviews(long id, String name, String description, String contactInfo) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.contactInfo = contactInfo;
	}

}
