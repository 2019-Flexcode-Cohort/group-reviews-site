package org.wcci.groupreviewssite.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findAllByReviews(List<Review> review);

}
