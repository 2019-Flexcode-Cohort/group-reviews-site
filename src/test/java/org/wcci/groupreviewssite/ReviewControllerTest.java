package org.wcci.groupreviewssite;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wcci.groupreviewssite.controllers.ReviewController;
import org.wcci.groupreviewssite.exceptions.ReviewNotFoundException;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.repositories.ReviewRepository;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleReviewToModel () throws ReviewNotFoundException {
		long newReviewId = 1;
		when(reviewRepo.findById(newReviewId)).thenReturn(Optional.of(review));
		underTest.findOneReview(newReviewId, model);
		verify(model).addAttribute("review", review);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review>allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
