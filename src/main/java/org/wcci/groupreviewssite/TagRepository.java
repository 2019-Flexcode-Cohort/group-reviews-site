package org.wcci.groupreviewssite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	List<Tag> findAllByReviews(List<Review> review);

}
