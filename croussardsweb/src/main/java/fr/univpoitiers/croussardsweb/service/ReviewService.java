package fr.univpoitiers.croussardsweb.service;

import fr.univpoitiers.croussardsweb.model.Restaurant;
import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.model.Student;
import fr.univpoitiers.croussardsweb.repository.RestaurantProxy;
import fr.univpoitiers.croussardsweb.repository.ReviewProxy;
import fr.univpoitiers.croussardsweb.repository.StudentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewProxy reviewProxy;

    @Autowired
    private RestaurantProxy restaurantProxy;

    @Autowired
    private StudentProxy studentProxy;

    public Review getReview(Long id) {
        return reviewProxy.getReview(id);
    }

    public Iterable<Review> getReviews() {
        return reviewProxy.getReviews();
    }

    public Iterable<Review> getReviewsByStudentId(Long id){
        return reviewProxy.getReviewsByStudentId(id); }

    public Iterable<Review> getReviewsByRestaurantId(Long id){
        return reviewProxy.getReviewsByRestaurantId(id);
    }

    public Review createReview(Review review, Long restaurantId, Long studentId) {
        Restaurant restaurant = restaurantProxy.getRestaurant(restaurantId);
        Student student = studentProxy.getStudent(studentId);

        review.setRestaurant(restaurant);
        review.setStudent(student);

        return reviewProxy.createReview(review);
    }
}