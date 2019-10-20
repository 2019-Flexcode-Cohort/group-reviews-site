package org.wcci.groupreviewssite;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private CategoryRepository categoryRepo;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@MockBean
	private ReviewRepository reviewRepo;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;

	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long newCategoryId = 1;
		when(categoryRepo.findById(newCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long newCategoryId = 1;
		when(categoryRepo.findById(newCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkForSingleCategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("category", is(category)));
	}

	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
	}

	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		mvc.perform(get("/categories")).andExpect(model().attribute("categories", is(allCategories)));
	}

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long newReviewId = 12;
		when(reviewRepo.findById(newReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/singleReview?id=12")).andExpect(view().name(is("singleReview")));
	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long newReviewId = 12;
		when(reviewRepo.findById(newReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/singleReview?id=12")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/singleReview?id=12")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(12L)).thenReturn(Optional.of(review));
		mvc.perform(get("/singleReview?id=12")).andExpect(model().attribute("review", is(review)));
	}

	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/allReviews")).andExpect(view().name(is("allReviews")));
	}

	@Test
	public void shouldeOkForAllReviews() throws Exception {
		mvc.perform(get("/allReviews")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/allReviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
}
