package fr.univpoitiers.croussardsweb.repository;

import fr.univpoitiers.croussardsweb.configuration.CustomProperties;
import fr.univpoitiers.croussardsweb.model.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestaurantProxy {

    @Autowired
    private CustomProperties customProperties;

    public Iterable<Restaurant> getRestaurants() {
        String baseApiUrl = customProperties.getApiUrl();
        String getRestaurantsUrl = baseApiUrl + "/restaurants";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Restaurant>> response = restTemplate.exchange(
                getRestaurantsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        log.debug("Get Restaurants call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Restaurant getRestaurant(Long id) {
        String baseApiUrl = customProperties.getApiUrl();
        String getRestaurantUrl = baseApiUrl + "/restaurants/" + id.intValue();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Restaurant> response = restTemplate.exchange(
                getRestaurantUrl,
                HttpMethod.GET,
                null,
                Restaurant.class
        );

        log.debug("Get Restaurant call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteRestaurant(final int id) {
        String baseApiUrl = customProperties.getApiUrl();
        String deleteRestaurantUrl = baseApiUrl + "/restaurants/" + id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteRestaurantUrl);

        log.debug("Delete Restaurant call");
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        String baseApiUrl = customProperties.getApiUrl();
        String createRestaurantUrl = baseApiUrl + "/restaurants/" + restaurant.getIdResto();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Restaurant> response = restTemplate.exchange(
                createRestaurantUrl,
                HttpMethod.POST,
                null,
                Restaurant.class
        );

        log.debug("Create Restaurant call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        String baseApiUrl = customProperties.getApiUrl();
        String updateRestaurantUrl = baseApiUrl + "/restaurants/" + restaurant.getIdResto();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(updateRestaurantUrl, restaurant);

        log.debug("Update Restaurant call");

        return restaurant;
    }

}
