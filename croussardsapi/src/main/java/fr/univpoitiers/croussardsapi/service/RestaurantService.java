package fr.univpoitiers.croussardsapi.service;

import fr.univpoitiers.croussardsapi.model.Restaurant;
import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.repository.RestaurantRepository;
import fr.univpoitiers.croussardsapi.repository.ReviewRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Data
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Iterable<Review> getReviewsByRestaurant(Long id) {
        return reviewRepository.findByRestaurantId(id);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public void updateRestaurant(Restaurant restaurant, Restaurant updateRestaurant) {
        restaurant.setName(updateRestaurant.getName());
        restaurant.setAddress(updateRestaurant.getAddress());
        restaurant.setId(updateRestaurant.getId());
        restaurant.setTypeResto(updateRestaurant.getTypeResto());
    }

    public ResponseEntity<?> myResponse(Object body) {
        if (body != null) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}