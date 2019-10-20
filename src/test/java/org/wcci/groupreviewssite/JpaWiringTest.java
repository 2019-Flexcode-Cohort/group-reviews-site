package org.wcci.groupreviewssite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

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
		Review retrievedReview2 =reviewRepo.findById(testReview2.getId()).get();
		
		assertThat(retrievedCategory.getReviews(), containsInAnyOrder(testReview1, testReview2));
		assertThat(retrievedReview.getCategory(), is(testCategory));
		
	}

}
