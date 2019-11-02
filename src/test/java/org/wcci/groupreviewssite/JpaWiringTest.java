package org.wcci.groupreviewssite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
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
import org.wcci.groupreviewssite.models.Category;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.models.Tag;
import org.wcci.groupreviewssite.repositories.CategoryRepository;
import org.wcci.groupreviewssite.repositories.ReviewRepository;
import org.wcci.groupreviewssite.repositories.TagRepository;

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
		
		Review testReview1= new Review("name", "description","contactInfo", "imageUrl");
		testReview1 = reviewRepo.save(testReview1);	
		Review testReview2= new Review("name1", "description1","contactInfo1", "imageUrl1");
		testReview2 = reviewRepo.save(testReview2);	
		
		Category testCategory = new Category("name", testReview1, testReview2);
		testCategory = categoryRepo.save(testCategory);
		
		entityManager.flush();
		entityManager.clear();
		
		Category retrievedCategory = categoryRepo.findById(testCategory.getId()).get();
		
		assertThat(retrievedCategory.getReviews(), containsInAnyOrder(testReview1, testReview2));
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
	
	@Test
	public void shouldGenerateReviewId() {
		Category category = new Category("name");
		Review review = reviewRepo.save(new Review("review title", "description", "imageUrl", "starImage", category));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(reviewId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishCategroyToReviewsRelationship() {
		Review review1 = reviewRepo.save(new Review("review 1", "description", "contact info", "imageUrl"));
		Review review2 = reviewRepo.save(new Review("review 2", "description", "contact info", "imageUrl"));

		Category category = new Category("Test Category", review1, review2);
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();

		assertThat(category.getReviews(), containsInAnyOrder(review2, review1));
	}
}
