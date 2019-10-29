package org.wcci.groupreviewssite.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.groupreviewssite.models.Tag;
import org.wcci.groupreviewssite.repositories.TagRepository;

@RequestMapping("/tags")
@RestController
public class TagController {
	
	@Resource
	private TagRepository tagRepo;
	 
	@GetMapping("")
	public Iterable<Tag> fetchTags() { 
		return tagRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Tag fetchOneTag(@PathVariable Long id) {
		return tagRepo.findById(id).get();
	}
	

}
