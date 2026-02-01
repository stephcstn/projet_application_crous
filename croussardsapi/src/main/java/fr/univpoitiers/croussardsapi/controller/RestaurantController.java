package fr.univpoitiers.croussardsapi.controller;

import fr.univpoitiers.croussardsapi.model.Restaurant;
import fr.univpoitiers.croussardsapi.model.Review;
import fr.univpoitiers.croussardsapi.service.RestaurantService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants") // Utilisation du mapping de base pour simplifier les autres méthodes
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable Long id) {
        Restaurant r = restaurantService.getRestaurant(id);
        return restaurantService.myResponse(r);
    }

    @GetMapping
    public ResponseEntity<?> getRestaurants() {
        Iterable<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurantService.myResponse(restaurants);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> getReviewsByRestaurant(@PathVariable Long id) {
        Iterable<Review> reviewsRestaurant = restaurantService.getReviewsByRestaurant(id);
        return restaurantService.myResponse(reviewsRestaurant);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
        Restaurant updateRestaurant = restaurantService.getRestaurant(id);
        restaurantService.updateRestaurant(updateRestaurant, restaurant);
        Restaurant savedUpdatedRestaurant = restaurantService.saveRestaurant(updateRestaurant);
        return restaurantService.myResponse(savedUpdatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}