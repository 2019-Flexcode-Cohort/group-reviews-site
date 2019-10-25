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
import org.wcci.groupreviewssite.controllers.CategoryController;
import org.wcci.groupreviewssite.exceptions.CategoryNotFoundException;
import org.wcci.groupreviewssite.models.Category;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.repositories.CategoryRepository;
import org.wcci.groupreviewssite.repositories.ReviewRepository;

public class CategoryControllerTest {

	@InjectMocks
	private CategoryController underTest;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;
	@Mock
	private Category anotherCategory;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private Review anotherReview;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel () throws CategoryNotFoundException{
		long newCategoryId = 1;
		when(categoryRepo.findById(newCategoryId)).thenReturn(Optional.of(category));
		underTest.findOneCategory(newCategoryId, model);
		verify(model).addAttribute("category", category);
	}
	
	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category>allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
	
	
	

	
	
	
	
}
