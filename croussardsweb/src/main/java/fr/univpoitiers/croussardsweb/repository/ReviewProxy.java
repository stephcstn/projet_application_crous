package fr.univpoitiers.croussardsweb.repository;

import fr.univpoitiers.croussardsweb.configuration.CustomProperties;
import fr.univpoitiers.croussardsweb.model.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ReviewProxy {

    @Autowired
    private CustomProperties customProperties;

    public Iterable<Review> getReviews() {
        String baseApiUrl = customProperties.getApiUrl();
        String getReviewsUrl = baseApiUrl + "/reviews";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Review>> response = restTemplate.exchange(
                getReviewsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Review>>() {}
        );

        log.debug("Get Reviews call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Review getReview(Long id) {
        String baseApiUrl = customProperties.getApiUrl();
        String getReviewUrl = baseApiUrl + "/reviews/" + id.intValue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Review> response = restTemplate.exchange(
                getReviewUrl,
                HttpMethod.GET,
                null,
                Review.class
        );

        log.debug("Get Review call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteReview(final int id) {
        String baseApiUrl = customProperties.getApiUrl();
        String deleteReviewUrl = baseApiUrl + "/reviews/" + id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteReviewUrl);

        log.debug("Delete Review call");
    }

    public Review createReview(Review review) {
        String baseApiUrl = customProperties.getApiUrl();
        String createReviewUrl = baseApiUrl + "/reviews/" + review.getIdReview();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Review> response = restTemplate.exchange(
                createReviewUrl,
                HttpMethod.POST,
                null,
                Review.class
        );

        log.debug("Create Review call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Review updateReview(Review review) {
        String baseApiUrl = customProperties.getApiUrl();
        String updateReviewUrl = baseApiUrl + "/reviews/" + review.getIdReview();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(updateReviewUrl, review);

        log.debug("Update Review call");

        return review;
    }

}