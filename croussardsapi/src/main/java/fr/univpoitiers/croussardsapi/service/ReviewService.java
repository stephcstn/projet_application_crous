package fr.univpoitiers.croussardsapi.service;

import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.repository.ReviewRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Data
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
    }

    public Iterable<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public void updateReview(Review review, Review updateReview) {
        review.setNbStars(updateReview.getNbStars());
        review.setComments(updateReview.getComments());
        review.setDatePubli(updateReview.getDatePubli());
        review.setStudent(updateReview.getStudent());
        review.setRestaurant(updateReview.getRestaurant());
        review.setIdReview(updateReview.getIdReview());
    }

    public ResponseEntity<?> myResponse(Object body) {
        if (body != null) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
