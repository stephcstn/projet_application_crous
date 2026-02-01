package fr.univpoitiers.croussardsweb.service;

import fr.univpoitiers.croussardsweb.model.Restaurant;
import fr.univpoitiers.croussardsweb.model.Review;
import fr.univpoitiers.croussardsweb.repository.RestaurantProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantProxy restaurantProxy;

    public Restaurant getRestaurant(Long id) {
        return restaurantProxy.getRestaurant(id);
    }

    public Iterable<Restaurant> getRestaurants() {
        return restaurantProxy.getRestaurants();
    }

    public void deleteRestaurant(final int id) {
        restaurantProxy.deleteRestaurant(id);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        Restaurant savedRestaurant;

        if (restaurant.getIdResto() == null) {
            savedRestaurant = restaurantProxy.createRestaurant(restaurant);
        } else {
            savedRestaurant = restaurantProxy.updateRestaurant(restaurant);
        }

        return savedRestaurant;
    }

   /* public Iterable<Review> getReviewsByRestaurant(Long id) {
        Restaurant restaurant = getRestaurant(id);


    }

    */

    public ResponseEntity<Restaurant> responseRestaurant(Restaurant restaurant) {
        if (restaurant == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    public ResponseEntity<Iterable<Restaurant>> responseRestaurants(Iterable<Restaurant> restaurants){
        if (restaurants == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurants);
    }
}
