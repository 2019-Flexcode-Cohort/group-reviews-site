package org.wcci.groupreviewssite.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.models.Tag;
import org.wcci.groupreviewssite.repositories.ReviewRepository;
import org.wcci.groupreviewssite.repositories.TagRepository;

@RequestMapping("/tags")
@RestController
public class TagController {

	@Resource
	private TagRepository tagRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@GetMapping("")
	public Iterable<Tag> fetchTags() {
		return tagRepo.findAll();
	}

	@GetMapping("/{id}")
	public Tag fetchOneTag(@PathVariable Long id) {
		return tagRepo.findById(id).get();
	}

	@PostMapping("/add-tag-to-review/{reviewId}/{tagName}")
	public Tag addTagToReview(@PathVariable long reviewId, @PathVariable String tagName) {
		Review review = reviewRepo.findById(reviewId).get();
		Tag tag = new Tag("#" + tagName, review);
		return tagRepo.save(tag);
	}

	@GetMapping("tags-for-review/{id}")
	public Iterable<Tag> fetchTagsForReview(@PathVariable Long id) {
		Review review = reviewRepo.findById(id).get();
		return review.getTags();
	}

	@DeleteMapping("/{tagId}/{reviewId}")
	public Iterable<Tag> deleteReviewTag(@PathVariable Long tagId, @PathVariable Long reviewId) {
		Review review = reviewRepo.findById(reviewId).get();
		Tag tagToRemove = tagRepo.findById(tagId).get();
		tagToRemove.getReviews().remove(review);
		tagRepo.save(tagToRemove);
		return reviewRepo.findById(reviewId).get().getTags();
		
	}
}
