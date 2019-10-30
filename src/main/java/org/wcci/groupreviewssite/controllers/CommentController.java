package org.wcci.groupreviewssite.controllers;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.groupreviewssite.exceptions.CommentNotFoundException;
import org.wcci.groupreviewssite.models.Comment;
import org.wcci.groupreviewssite.repositories.CommentRepository;

@Controller
public class CommentController {

	@Resource
	private CommentRepository commentRepo;
	
	
	
	@RequestMapping("/singleComment")
	public String findOneComment(@RequestParam(value="id")long id, Model model) throws CommentNotFoundException {
		Optional<Comment>comment = commentRepo.findById(id);
		
		if(comment.isPresent()) {
			model.addAttribute("comment", comment.get());
			return "singleComment";
		}
		throw new CommentNotFoundException();	
	}


	@RequestMapping("/allComments")
	public String findAllComments(Model model) {
		model.addAttribute("comments", commentRepo.findAll());
		return "allComments";	
	}
	
}
