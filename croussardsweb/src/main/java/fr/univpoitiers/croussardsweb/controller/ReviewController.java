package fr.univpoitiers.croussardsweb.controller;

import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public String reviewsPage(Model model) {
        Iterable<Review> reviews = reviewService.getReviews();

        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        model.addAttribute("reviews", reviews);
        return "student_reviews";
    }

    @GetMapping("/reviews/{id}")
    public String getReview(Model model, @PathVariable Long id) {
        Review review = reviewService.getReview(id);

        if (review == null) {
            return "student_reviews";
        }

        model.addAttribute("review", review);
        return "review";
    }


}
