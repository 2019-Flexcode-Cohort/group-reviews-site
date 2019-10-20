package org.wcci.groupreviewssite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaWiringTest {
	@Resource
	private CategoryRepository categoryRepo;
	@Resource
	private ReviewRepository reviewRepo;
	@Resource
	private TagRepository tagRepo;
	@Resource
	TestEntityManager entityManager;
	
	@Test	
	public void categoryShouldHaveACollectionOfReviews() {
		Category testCategory = new Category("name");
		testCategory = categoryRepo.save(testCategory);
		Review testReview1= new Review("name", "description","contactInfo", "imageUrl", testCategory);
		testReview1 = reviewRepo.save(testReview1);	
		Review testReview2= new Review("name1", "description1","contactInfo1", "imageUrl1", testCategory);
		testReview2 = reviewRepo.save(testReview2);	
		
		entityManager.flush();
		entityManager.clear();
		
		Category retrievedCategory = categoryRepo.findById(testCategory.getId()).get();
		Review retrievedReview = reviewRepo.findById(testReview1.getId()).get();
		
		assertThat(retrievedCategory.getReviews(), containsInAnyOrder(testReview1, testReview2));
		assertThat(retrievedReview.getCategory(), is(testCategory));
	}
	
	@Test
	public void shouldSaveAndLoadReview() {
		Category testCategory = new Category("name");
		testCategory = categoryRepo.save(testCategory);
		Review review = new Review("review1","description","contactInfo","imageUrl", testCategory);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review1"));
	}
	@Test
	public void shouldBeOkForSingleReview() {
	}
	
	@Test
	public void shouldSaveAndLoadTag() {
	Category testCategory = new Category ("name");
	testCategory = categoryRepo.save(testCategory);
	Review review = new Review ("review1","description","contactInfo","imageUrl", testCategory);
	review = reviewRepo.save(review);
	Tag testTag = new Tag ("TestTag", review );
	testTag = tagRepo.save(testTag);
	}
	
	@Test
	public void retrieveTagsByReviewId() {
		Category testCategory = new Category ("name");
		testCategory = categoryRepo.save(testCategory);
		Review review = new Review ("review1","description","contactInfo","imageUrl", testCategory);
		review = reviewRepo.save(review);
		Tag testTag = new Tag ("TestTag", review );
		testTag = tagRepo.save(testTag);
		Tag testTag2 = new Tag ("AnotherTestTag");
		testTag2 = tagRepo.save(testTag2);
		
		entityManager.flush();
		entityManager.clear();
		
		List<Tag> retrieveTags = tagRepo.findAllByReviews(Collections.singletonList(review));
		assertThat (retrieveTags,contains(testTag));
		
		
	}
}
