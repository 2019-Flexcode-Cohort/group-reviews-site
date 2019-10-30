package org.wcci.groupreviewssite;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wcci.groupreviewssite.controllers.CommentController;
import org.wcci.groupreviewssite.controllers.ReviewController;
import org.wcci.groupreviewssite.exceptions.CommentNotFoundException;
import org.wcci.groupreviewssite.models.Comment;
import org.wcci.groupreviewssite.repositories.CommentRepository;

public class CommentControllerTest {

	@InjectMocks
	private CommentController underTest;
	
	@Mock
	private CommentRepository commentRepo;
	
	@Mock
	private Comment comment;
	
	@Mock
	private Comment anotherComment;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCommentToReview() throws CommentNotFoundException {		
		long commentId = 1;			
		when(commentRepo.findById(commentId)).thenReturn(Optional.of(comment));
		underTest.findOneComment(commentId, model);
		verify(model).addAttribute("comment", comment);	
	}
	
	@Test
	public void shouldAddAllCommentsToModel() {
		Collection<Comment> allComments = Arrays.asList(comment, anotherComment);
		when(commentRepo.findAll()).thenReturn(allComments);
		underTest.findAllComments(model);
		verify(model).addAttribute("comments", allComments);
	}
}
