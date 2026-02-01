package fr.univpoitiers.croussardsapi.controller;

import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.service.ReviewService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        return reviewService.myResponse(review);
    }

    @GetMapping
    public ResponseEntity<?> getReviews() {
        Iterable<Review> reviews = reviewService.getReviews();
        return reviewService.myResponse(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @Valid @RequestBody Review review) {
        Review updateReview = reviewService.getReview(id);
        reviewService.updateReview(updateReview, review);
        Review savedUpdatedReview = reviewService.saveReview(updateReview);
        return reviewService.myResponse(savedUpdatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}