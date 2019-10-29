package org.wcci.groupreviewssite.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.groupreviewssite.exceptions.ReviewNotFoundException;
import org.wcci.groupreviewssite.models.Review;
import org.wcci.groupreviewssite.repositories.ReviewRepository;

@Controller
public class ReviewController {


@Resource
private ReviewRepository reviewRepo;



@RequestMapping("/singleReview")
public String findOneReview(@RequestParam(value="id") long id, Model model) throws ReviewNotFoundException {
	Optional<Review> review = reviewRepo.findById(id);

	if(review.isPresent()) {
		model.addAttribute("review", review.get());
		return "singleReview";
	}
	throw new ReviewNotFoundException();
}

@RequestMapping("/allReviews")
public String findAllReviews(Model model) {
	model.addAttribute("reviews", reviewRepo.findAll());
	return "allReviews";
}


}

































<<<<<<< HEAD
=======
}
>>>>>>> master
