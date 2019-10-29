package org.wcci.groupreviewssite.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.wcci.groupreviewssite.models.Category;
import org.wcci.groupreviewssite.models.Review;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Collection<Category> findByReviewsContains(Review review);
	Collection<Category> findByReviewsId(long id);
}
