package fr.univpoitiers.croussardsweb.service;

import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.repository.ReviewProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewProxy reviewProxy;

    public Review getReview(Long id) {
        return reviewProxy.getReview(id);
    }

    public Iterable<Review> getReviews() {
        return reviewProxy.getReviews();
    }

    public void deleteReview(final int id) {
        reviewProxy.deleteReview(id);
    }

    public Review saveReview(Review review) {
        Review savedReview;

        if (review.getIdReview() == null) {
            savedReview = reviewProxy.createReview(review);
        } else {
            savedReview = reviewProxy.updateReview(review);
        }

        return savedReview;
    }

    public ResponseEntity<Review> responseReview(Review review) {
        if (review == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(review);
    }

    public ResponseEntity<Iterable<Review>> responseReviews(Iterable<Review> reviews){
        if (reviews == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviews);
    }
}