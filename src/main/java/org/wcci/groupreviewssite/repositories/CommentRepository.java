package org.wcci.groupreviewssite.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.groupreviewssite.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
