package org.wcci.groupreviewssite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wcci.groupreviewssite.controllers.TagController;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.models.Tag;
import org.wcci.groupreviewssite.repositories.ReviewRepository;
import org.wcci.groupreviewssite.repositories.TagRepository;

public class TagControllerTest {

	@InjectMocks
	private TagController underTest;
	@Mock
	private ReviewRepository reviewRepo;
	@Mock
	private TagRepository tagRepo;
	@Mock
	private Model model;
	@Mock
	private Tag tag;
	@Mock
	private Review review;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldNewTagToReview() {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		Tag tagToAdd = new Tag("#test", review);
		underTest.addTagToReview(1L, "#test");
		verify(tagRepo).save(tagToAdd);
		
	}

}
