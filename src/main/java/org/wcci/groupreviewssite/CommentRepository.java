package org.wcci.groupreviewssite;

import org.springframework.data.repository.CrudRepository;
import org.wcci.groupreviewssite.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
