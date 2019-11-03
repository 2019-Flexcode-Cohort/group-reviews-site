package org.wcci.groupreviewssite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.groupreviewssite.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	
}
